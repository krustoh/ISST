package cn.edu.zju.isst.web.controller;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.entity.JobSearchCondition;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.form.JobForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.CategoryService;
import cn.edu.zju.isst.service.CityService;
import cn.edu.zju.isst.service.JobCommentService;
import cn.edu.zju.isst.service.JobService;

@RequireUser
@Controller("webJobController")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CityService cityService;
    @Autowired
    private JobCommentService jobCommentService;
    
    @RequestMapping(value = "/jobs/categories/{categoryAlias}.html", method = RequestMethod.GET)
    public String list(Model model,
            JobSearchCondition condition,
            @PathVariable("categoryAlias") String categoryAlias, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        Category category = categoryService.find(categoryAlias);
        condition.setStatus(Job.STATUS_PUBLISHED);
        if (null != category) {
            condition.setCategoryId(category.getId());
            model.addAttribute("category", category);
            model.addAttribute("condition", condition);
            model.addAttribute("jobs", jobService.findAll(condition, 10, page));
        } else {
            throw new RuntimeException("Category does not exist.");
        }
        return "jobs/list";
    }
    
    @RequestMapping(value = "/users/jobs/categories/{categoryAlias}.html", method = RequestMethod.GET)
    public String userList(Model model,
            JobSearchCondition condition,
            @PathVariable("categoryAlias") String categoryAlias, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        Category category = categoryService.find(categoryAlias);
        if (null != category) {
            condition.setCategoryId(category.getId());
            condition.setUserId(user.getId());
            model.addAttribute("category", category);
            model.addAttribute("condition", condition);
            model.addAttribute("jobs", jobService.findAll(condition, 10, page));
        } else {
            throw new RuntimeException("Category does not exist.");
        }
        return "jobs/users/list";
    }
    
    @RequestMapping(value = "/users/jobs/categories/{categoryAlias}/add.html", method = RequestMethod.GET)
    public String add(@PathVariable("categoryAlias") String categoryAlias, Model model) {
        if (!categoryAlias.equals("recommend")) {
            WebUtils.addErrorFlashMessage("您无权限");
            return WebUtils.redirectUrl("/users/jobs/categories/recommend.html");
        }
        model.addAttribute("category", categoryService.find(categoryAlias));
        model.addAttribute("cities", cityService.findAllForSelect());
        JobForm form = new JobForm();
        model.addAttribute("jobForm", form);
        return "jobs/users/form";
    }
    
    @RequestMapping(value = "/users/jobs/{id}.html", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        Job job = jobService.find(id);
        Category category = categoryService.find(job.getCategoryId());
        if (null != job && job.getUserId() != user.getId()) {
            WebUtils.addErrorFlashMessage("您无权限");
            return WebUtils.redirectUrl("/users/jobs/categories/" + category.getAlias() + ".html");
        }
        
        model.addAttribute("category", category);
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("jobForm", new JobForm(job));
        return "jobs/users/form";
    }
    
    @RequestMapping(value = "/users/jobs/categories/{categoryAlias}/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @PathVariable("categoryAlias") String categoryAlias, 
            Model model, 
            @Valid JobForm form, 
            BindingResult result, 
            HttpSession session) {
        return save(form, result, null, categoryService.find(categoryAlias), model, session);
    }
    
    @RequestMapping(value = "/users/jobs/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @PathVariable("id") int id, 
            Model model, 
            @Valid JobForm form, 
            BindingResult result, 
            HttpSession session) {
        form.setId(id);
        Job job = jobService.find(form.getId());
        return save(form, result, job, categoryService.find(job.getCategoryId()), model, session);
    }
        
    private String save(JobForm form, BindingResult result, Job job, Category category, Model model, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("cities", cityService.findAllForSelect());
            model.addAttribute("jobForm", form);
            return "jobs/users/form";
        } else {
            form.setStatus(Job.STATUS_HIDDEN);
            form.setCategoryId(category.getId());
            form.setUserId(user.getId());
            if (form.getId() > 0) {
                if (null != job && job.getUserId() != user.getId()) {
                    WebUtils.addErrorFlashMessage("您无权限");
                    return WebUtils.redirectUrl("/users/jobs/categories/" + category.getAlias() + ".html");
                }
            }

            if (null == job) {
                job = form.build();
            } else {
                form.bind(job);
            }

            jobService.save(job);
            WebUtils.addSuccessFlashMessage(String.format("成功保存 <i>%s</i>", job.getTitle()));
            return WebUtils.redirectUrl("/users/jobs/categories/" + category.getAlias() + ".html");
        }
    }
    
    @RequestMapping(value = "/users/jobs/categories/{categoryAlias}/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            @PathVariable("categoryAlias") String categoryAlias,
            Model model,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        Category category = categoryService.find(categoryAlias);
        
        if (confirm == 0) {
            model.addAttribute("entities", jobService.findAll(idset));
            model.addAttribute("navigationActiveKey", "users_job_" + category.getAlias());
            model.addAttribute("cancelUrl", WebUtils.createUrl("/users/jobs/categories/" + category.getAlias() + ".html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                Job job = jobService.find(Integer.valueOf(ids[0]).intValue());
                if (null != job) {
                    if (user.getId() != job.getUserId()) {
                        WebUtils.addErrorFlashMessage(String.format("您无权限删除：<i>%s</i>", job.getTitle()));
                    } else {
                        jobService.delete(job);
                        WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", job.getTitle()));
                    }
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                boolean allow = true;
                for (Job job : jobService.findAll(idset)) {
                    if (user.getId() != job.getUserId()) {
                        WebUtils.addErrorFlashMessage(String.format("您无权限删除：<i>%s</i>", job.getTitle()));
                        allow = false;
                        break;
                    } 
                }
                
                if (allow) {
                    int count = jobService.delete(idset);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 条记录", count));
                }
            }
            
            return WebUtils.redirectUrl("/users/jobs/categories/"+category.getAlias()+".html");
        }
    }
    
    @RequestMapping(value = "/jobs/{id}.html", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") int id) {
        Job job = jobService.find(id);
        model.addAttribute("job", job);
        
        Category category = categoryService.find(job.getCategoryId());
        model.addAttribute("category", category);
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("comments", jobCommentService.findAll(id, 10, 1));
        
        return "jobs/view";
    }
}