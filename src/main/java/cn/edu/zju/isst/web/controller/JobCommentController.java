package cn.edu.zju.isst.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.JobComment;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.CategoryService;
import cn.edu.zju.isst.service.JobCommentService;
import cn.edu.zju.isst.service.JobService;
import cn.edu.zju.isst.service.UserService;

@RequireUser
@Controller("webJobCommentController")
public class JobCommentController {
    @Autowired
    private JobService jobService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private JobCommentService jobCommentService;
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/jobs/{jobId}/comments.html", method = RequestMethod.GET)
    public String list(
            @PathVariable("jobId") int jobId, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model) {
        Job job = jobService.find(jobId);
        model.addAttribute("job", job);
        model.addAttribute("category", categoryService.find(job.getCategoryId()));
        model.addAttribute("comments", jobCommentService.findAll(jobId, 20, page));
        
        return "jobs/comments/list";
    }
    
    @RequestMapping(value = "/jobs/{jobId}/comments", method = RequestMethod.POST)
    public String save(
            @PathVariable("jobId") int jobId,
            @RequestParam("content") String content,
            HttpSession session) {
    	boolean error = false;
    	
        if (null == content || ((content = content.trim().replaceAll("&([#a-zA-Z0-9]+);", "").replaceAll("\\<.*?>", "")).isEmpty())) {
            WebUtils.addErrorFlashMessage("评论内容不能为空");
            error = true;
        }
        
        if (content.length() > 80) {
            WebUtils.addErrorFlashMessage("评论内容的长度不能超过80个字符");
            error = true;
        }
        
        if (!error) {
        	StudentUser user = (StudentUser) session.getAttribute("user");
            
            JobComment jobComment = new JobComment();
            jobComment.setUserId(user.getId());
            jobComment.setJobId(jobId);
            jobComment.setContent(content);
            jobCommentService.save(jobComment);
            
            WebUtils.addSuccessFlashMessage("评论成功！");
        }
        
        return WebUtils.redirectUrl("/jobs/"+jobId+"/comments.html");
    }
}