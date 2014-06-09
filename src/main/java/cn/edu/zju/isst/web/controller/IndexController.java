package cn.edu.zju.isst.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.identity.RequireUser;

@Controller("webIndexController")
public class IndexController {
    @RequireUser
    @RequestMapping({"/", "/index.html"})
    public String index(HttpServletRequest request) {
    	return WebUtils.redirectUrl("/archives/categories/campus.html");
    }
}