package cn.edu.zju.isst.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.form.AdministratorForm;
import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.form.AdministratorLoginForm;

import java.util.Set;

public interface AdministratorService {
    public boolean login(HttpServletRequest request, HttpServletResponse response, AdministratorLoginForm form, BindingResult result);
    public void logout(HttpServletRequest request, HttpServletResponse response);
    public void changePassword(int id, String password);
    public PaginationList<Administrator> findAll(int pageSize, int page);
    public Administrator find(int id);
    public Result save(AdministratorForm form);
    public int delete(Administrator administrator);
    public int delete(Set<Integer> idset);
}