package cn.edu.zju.isst.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.zju.isst.service.UserService;
import cn.edu.zju.isst.entity.Administrator;
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
}