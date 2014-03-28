package cn.edu.zju.isst.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zju.isst.common.FlashMessage;
import cn.edu.zju.isst.form.UserLoginForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("userLoginForm", new UserLoginForm());
        return "login";
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(@Valid UserLoginForm form, BindingResult result, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (!result.hasErrors() && null != userService.login(request, response, form, result)) {
            return "redirect:index.html";
        } else {
            model.addAttribute("userLoginForm", form);
            model.addAttribute("flash_message", FlashMessage.error(result));
            return "login";
        }
    }
    
    @RequireUser
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        userService.logout(request, response);
        return "redirect:login.html";
    }
}