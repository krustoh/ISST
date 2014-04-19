package cn.edu.zju.isst.admin.controller;

import java.util.HashSet;
import java.util.Set;

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
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.JobSearchCondition;
import cn.edu.zju.isst.form.JobForm;
import cn.edu.zju.isst.service.CategoryService;
import cn.edu.zju.isst.service.CityService;
import cn.edu.zju.isst.service.JobService;

@Controller("adminJobController")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CityService cityService;
    
    @RequestMapping(value = "/jobs/categories/{categoryAlias}.html", method = RequestMethod.GET)
    public String list(Model model,
            JobSearchCondition condition,
            @PathVariable("categoryAlias") String categoryAlias, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        Category category = categoryService.find(categoryAlias);
        if (null != category) {
            model.addAttribute("category", category);
            model.addAttribute("condition", condition);
            model.addAttribute("jobs", jobService.findAll(category.getId(), condition, 10, page));
        } else {
            throw new RuntimeException("Category does not exist.");
        }
        return "jobs/list";
    }
    
    @RequestMapping(value = "/jobs/{id}.html", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        Job job = jobService.find(id);
        model.addAttribute("jobForm", new JobForm(job));
        
        Category category = categoryService.find(job.getCategoryId());
        model.addAttribute("category", category);
        model.addAttribute("cities", cityService.findAllForSelect());
        
        return "jobs/form";
    }
    
    @RequestMapping(value = "/jobs/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @Valid JobForm form, 
            BindingResult result, 
            @PathVariable("id") int id,
            Model model) {
        form.setId(id);
        Category category = categoryService.find(form.getCategoryId());
        if (result.hasErrors()) {
            model.addAttribute("cities", cityService.findAllForSelect());
            model.addAttribute("category", category);
            model.addAttribute("jobForm", form);
            return "jobs/form";
        } else {
            Job job = jobService.find(id);
            if (null != job) {
                form.bind(job);
                jobService.save(job);
            }
            WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", job.getTitle()));
            return WebUtils.redirectAdminUrl("jobs/categories/" + category.getAlias() + ".html");
        }
    }
    
    @RequestMapping(value = "/jobs/categories/{categoryAlias}/add.html", method = RequestMethod.GET)
    public String add(Model model, @PathVariable("categoryAlias") String categoryAlias) {
        Category category = categoryService.find(categoryAlias);
        model.addAttribute("category", category);
        model.addAttribute("cities", cityService.findAllForSelect());
        
        JobForm form = new JobForm();
        form.setCategoryId(category.getId());
        model.addAttribute("jobForm", form);
        
        return "jobs/form";
    }
    
    @RequestMapping(value = "/jobs/categories/{categoryAlias}/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @Valid JobForm form, 
            BindingResult result, 
            @PathVariable("categoryAlias") String categoryAlias,
            Model model) {
        Category category = categoryService.find(categoryAlias);
        form.setCategoryId(category.getId());
        
        if (result.hasErrors()) {
            model.addAttribute("cities", cityService.findAllForSelect());
            model.addAttribute("category", category);
            model.addAttribute("jobForm", form);
            return "jobs/form";
        } else {
            Job job = jobService.save(form.build());
            WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", job.getTitle()));
            return WebUtils.redirectAdminUrl("jobs/categories/" + category.getAlias() + ".html");
        }
    }
    
    @RequestMapping(value = "/jobs/categories/{categoryAlias}/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            @PathVariable("categoryAlias") String categoryAlias, 
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        Category category = categoryService.find(categoryAlias);
        
        if (confirm == 0) {
            model.addAttribute("entities", jobService.findAll(idset));
            model.addAttribute("navigationActiveKey", "job_" + category.getAlias());
            model.addAttribute("cancelUrl", WebUtils.createAdminUrl("jobs/categories/" + category.getAlias() + ".html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                Job job = jobService.find(Integer.valueOf(ids[0]).intValue());
                if (null != job) {
                    jobService.delete(job);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", job.getTitle()));
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                int count = jobService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 条记录", count));
            }
            
            return WebUtils.redirectAdminUrl("jobs/categories/"+category.getAlias()+".html");
        }
    }
    
    @RequestMapping(value = "/jobs/categories/{categoryAlias}/publish")
    public String publish(
            @RequestParam("id[]") String[] ids,
            @PathVariable("categoryAlias") String categoryAlias) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        int count = 0;
        if (!idset.isEmpty()) {
            count = jobService.publish(idset);
        }
        
        Category category = categoryService.find(categoryAlias);
        WebUtils.addSuccessFlashMessage(String.format("成功发布 <i>%d</i> 条记录", count));
        return WebUtils.redirectAdminUrl("jobs/categories/"+category.getAlias()+".html");
    }
    
    @RequestMapping(value = "/jobs/categories/{categoryAlias}/hide")
    public String hide(
            @RequestParam("id[]") String[] ids,
            @PathVariable("categoryAlias") String categoryAlias) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        int count = 0;
        if (!idset.isEmpty()) {
            count = jobService.hide(idset);
        }
        
        Category category = categoryService.find(categoryAlias);
        WebUtils.addSuccessFlashMessage(String.format("成功隐藏 <i>%d</i> 条记录", count));
        return WebUtils.redirectAdminUrl("jobs/categories/"+category.getAlias()+".html");
    }
}