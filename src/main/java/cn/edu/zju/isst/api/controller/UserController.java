package cn.edu.zju.isst.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.form.UserLoginForm;
import cn.edu.zju.isst.service.UserService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/login")
    public @ResponseBody ApiResponse login(@Valid UserLoginForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        User user = null;
        if (!result.hasErrors() && (null != (user = userService.login(request, response, form, result)))) {
            return new ApiResponse(user);
        } else {
            return new ApiResponse(result);
        }
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public @ResponseBody ApiResponse logout(HttpServletRequest request, HttpServletResponse response) {
        User user = userService.logout(request, response);
        return new ApiResponse(user);
    }
}
