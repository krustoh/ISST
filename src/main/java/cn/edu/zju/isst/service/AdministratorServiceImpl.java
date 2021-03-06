package cn.edu.zju.isst.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.form.AdministratorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.dao.AdministratorDao;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.form.AdministratorLoginForm;
import cn.edu.zju.isst.identity.AdministratorIdentity;

import java.util.Set;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorDao administratorDao;
    
    @Override
    public boolean login(HttpServletRequest request, HttpServletResponse response, AdministratorLoginForm form, BindingResult result) {
        Administrator administrator = administratorDao.find(form.getUsername());
        if (null == administrator) {
            result.rejectValue("username", "username.not_exist", "用户名不存在！");
        } else if (!Administrator.validatePassword(administrator.getPassword(), form.getPassword())) {
            result.rejectValue("password", "password.invalid", "密码错误！");
        } else {
            AdministratorIdentity.login(request, response, administrator, form.isRememberMe());
            return true;
        }
        return false;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        AdministratorIdentity.logout(request, response);
    }

    @Override
    public void changePassword(int id, String password) {
        administratorDao.changePassword(id, password);
    }

    @Override
    public PaginationList<Administrator> findAll(int pageSize, int page) {
        return administratorDao.findAll(pageSize, page);
    }

    @Override
    public Administrator find(int id) {
        return administratorDao.find(id);
    }

    @Override
    public Result save(AdministratorForm form) {
        if (form.getId() > 0) {
            Administrator administrator = administratorDao.find(form.getId());
            form.bind(administrator);
            administratorDao.update(administrator);
        } else {
            administratorDao.insert(form.build());
        }

        return new Result();
    }

    @Override
    public int delete(Administrator administrator) {
        return administratorDao.delete(administrator);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return administratorDao.delete(idset);
    }
}