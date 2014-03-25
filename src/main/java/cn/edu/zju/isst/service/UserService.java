package cn.edu.zju.isst.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.form.UserLoginForm;

public interface UserService {
    public User login(HttpServletRequest request, HttpServletResponse response, UserLoginForm form, BindingResult result);
    public User logout(HttpServletRequest request, HttpServletResponse response);
}