package cn.edu.zju.isst.api.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.TaskService;

@RequireUser
@Controller("apiTaskController")
public class TaskController {
    @Autowired
    private TaskService taskService;
    
    @RequestMapping("/tasks")
    public @ResponseBody ApiResponse list(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        return new ApiResponse(taskService.findListForUser(user.getId(), pageSize, page).getItems());
    }
}