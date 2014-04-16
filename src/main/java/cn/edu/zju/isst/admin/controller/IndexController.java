package cn.edu.zju.isst.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.identity.RequireAdministrator;

@Controller
public class IndexController {
    @RequireAdministrator
    @RequestMapping({"/", "/index.html"})
    public String index(HttpServletRequest request) {
        Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
        if (administrator.hasRole(Administrator.ADMIN)) {
            return WebUtils.redirectAdminUrl("archives/categories/campus.html");
        } else if (administrator.hasRole(Administrator.ADMIN_ALUMNI)) {
            return WebUtils.redirectAdminUrl("alumni.html");
        }
        return null;
    }
}