package cn.edu.zju.isst.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.JobSearchCondition;
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