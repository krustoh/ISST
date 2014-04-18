package cn.edu.zju.isst.api.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.entity.JobComment;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.JobCommentService;
import cn.edu.zju.isst.service.UserService;

@RequireUser
@Controller("apiJobCommentController")
public class JobCommentController {
    @Autowired
    private JobCommentService jobCommentService;
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/jobs/{jobId}/comments", method = RequestMethod.GET)
    public @ResponseBody ApiResponse list(
            @PathVariable("jobId") int jobId, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return new ApiResponse(jobCommentService.findAll(jobId, pageSize, page).getItems());
    }
    
    @RequestMapping(value = "/jobs/{jobId}/comments", method = RequestMethod.POST)
    public @ResponseBody ApiResponse save(
            @PathVariable("jobId") int jobId,
            @RequestParam("content") String content,
            HttpSession session) {
        if (null == content || ((content = content.trim().replaceAll("&([#a-zA-Z0-9]+);", "").replaceAll("\\<.*?>", "")).isEmpty())) {
            return new ApiResponse(70, "评论内容不能为空");
        }
        
        if (content.length() > 80) {
            return new ApiResponse(71, "评论内容的长度不能超过80个字符");
        }
        
        StudentUser user = (StudentUser) session.getAttribute("user");
        
        JobComment jobComment = new JobComment();
        jobComment.setUserId(user.getId());
        jobComment.setJobId(jobId);
        jobComment.setContent(content);
        jobCommentService.save(jobComment);
        
        jobComment.setUser(userService.findUserSummary(user.getId()));
        
        return new ApiResponse(jobComment);
    }
}