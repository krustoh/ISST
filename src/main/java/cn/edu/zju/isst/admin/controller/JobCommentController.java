package cn.edu.zju.isst.admin.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.JobComment;
import cn.edu.zju.isst.service.CategoryService;
import cn.edu.zju.isst.service.JobCommentService;
import cn.edu.zju.isst.service.JobService;

@Controller("adminJobCommentController")
public class JobCommentController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JobCommentService jobCommentService;
    
    @RequestMapping(value = "/jobs/{jobId}/comments", method = RequestMethod.GET)
    public String list(
            @PathVariable("jobId") int jobId, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model) {
        Job job = jobService.find(jobId);
        model.addAttribute("job", job);
        model.addAttribute("category", categoryService.find(job.getCategoryId()));
        model.addAttribute("comments", jobCommentService.findAll(jobId, 10, page));
        
        return "jobs/comments/list";
    }
    
    @RequestMapping(value = "/jobs/{jobId}/comments/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            @PathVariable("jobId") int jobId,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        Job job = jobService.find(jobId);
        Category category = categoryService.find(job.getCategoryId());
        
        if (confirm == 0) {
            model.addAttribute("entities", jobCommentService.findAll(idset));
            model.addAttribute("navigationActiveKey", "job_" + category.getAlias());
            model.addAttribute("cancelUrl", WebUtils.createAdminUrl("jobs/" + jobId + "/comments.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                JobComment jobComment = jobCommentService.find(Integer.valueOf(ids[0]).intValue());
                if (null != jobComment) {
                    jobCommentService.delete(jobComment);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", jobComment.getContent()));
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                int count = jobCommentService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 条记录", count));
            }
            
            return WebUtils.redirectUrl("/jobs/"+jobId+"/comments.html");
        }
    }
}