package cn.edu.zju.isst.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.form.UserLoginForm;

public interface UserService {
    public User find(int id);
    public User login(HttpServletRequest request, HttpServletResponse response, UserLoginForm form, BindingResult result);
    public void updateLoginLocation(User user, double longitude, double latitude);
    public User logout(HttpServletRequest request, HttpServletResponse response);
    public void synchronizeUsers();
}