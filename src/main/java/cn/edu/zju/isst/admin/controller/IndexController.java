package cn.edu.zju.isst.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zju.isst.identity.RequireAdministrator;

@Controller
public class IndexController {
    @RequireAdministrator
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
}