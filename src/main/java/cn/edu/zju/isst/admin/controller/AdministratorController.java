package cn.edu.zju.isst.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zju.isst.admin.service.AdministratorService;
import cn.edu.zju.isst.common.FlashMessage;
import cn.edu.zju.isst.form.AdministratorLoginForm;
import cn.edu.zju.isst.identity.RequireAdministrator;

@Controller
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("administratorLoginForm", new AdministratorLoginForm());
        return "login";
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String loginSubmit(@Valid AdministratorLoginForm form, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!result.hasErrors() && administratorService.login(request, response, form, result)) {
            return "redirect:index.html";
        } else {
            model.addAttribute("administratorLoginForm", form);
            model.addAttribute("flash_message", FlashMessage.error(result));
            return "login";
        }
    }
    
    @RequireAdministrator
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        administratorService.logout(request, response);
        return "redirect:login.html";
    }
}