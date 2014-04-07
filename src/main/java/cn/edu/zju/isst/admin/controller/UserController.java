package cn.edu.zju.isst.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.service.UserService;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.identity.RequireAdministrator;

@RequireAdministrator(Administrator.ADMIN_ALUMNI)
@Controller("adminUserController")
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping("/synchronizeUsers")
    public String synchronizeUsers() {
        userService.synchronizeUsers();
        return "redirect:index.html";
    }
    
    @RequestMapping("/alumni")
    public String list(
            UserSearchCondition condition, 
            Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        
        model.addAttribute("users", userService.findAll(condition, 10, page));
        return "users/list";
    }
}