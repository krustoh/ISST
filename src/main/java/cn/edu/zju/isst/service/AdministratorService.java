package cn.edu.zju.isst.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.form.AdministratorLoginForm;

public interface AdministratorService {
    public boolean login(HttpServletRequest request, HttpServletResponse response, AdministratorLoginForm form, BindingResult result);
    public void logout(HttpServletRequest request, HttpServletResponse response);
}