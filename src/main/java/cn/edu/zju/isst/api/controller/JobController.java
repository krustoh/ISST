package cn.edu.zju.isst.api.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.JobSearchCondition;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.form.JobForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.CategoryService;
import cn.edu.zju.isst.service.JobService;

@RequireUser
@Controller("apiJobController")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping(value = "/jobs/categories/{categoryAlias}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse findAll(
            @PathVariable("categoryAlias") String categoryAlias,
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        JobSearchCondition condition = new JobSearchCondition();
        condition.setCategoryId(categoryService.find(categoryAlias).getId());
        condition.setKeywords(keywords);
        condition.setStatus(Job.STATUS_PUBLISHED);
        return new ApiResponse(jobService.findAll(condition, pageSize, page).getItems());
    }
    
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse find(@PathVariable("id") int id) {
        return new ApiResponse(jobService.find(id));
    }
    
    @RequestMapping(value = "/users/jobs/{categoryAlias}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse userList(
            @PathVariable("categoryAlias") String categoryAlias,
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        JobSearchCondition condition = new JobSearchCondition();
        condition.setUserId(user.getId());
        condition.setCategoryId(categoryService.find(categoryAlias).getId());
        condition.setKeywords(keywords);
        return new ApiResponse(jobService.findAll(condition, pageSize, page).getItems());
    }
    
    @RequestMapping(value = "/users/jobs/recommend", method = RequestMethod.POST)
    public @ResponseBody ApiResponse post(@Valid JobForm form, BindingResult result, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        if (result.hasErrors()) {
            return new ApiResponse(result);
        } else {
            form.setCategoryId(8);
            form.setUserId(user.getId());
            form.setStatus(Job.STATUS_HIDDEN);
            
            Job job = null;
            if (form.getId() > 0) {
                job = jobService.find(form.getId());
                if (null != job && job.getUserId() != user.getId()) {
                    return new ApiResponse(20, "您无权限");
                }
            }
            
            if (null == job) {
                job = form.build();
            } else {
                form.bind(job);
            }
            
            jobService.save(job);
            
            return new ApiResponse(job);
        }
    }
}
