package cn.edu.zju.isst.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.dao.UserDao;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.form.UserLoginForm;
import cn.edu.zju.isst.identity.UserIdentity;
import cn.edu.zju.isst.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    
    @Override
    public User login(HttpServletRequest request, HttpServletResponse response, UserLoginForm form, BindingResult result) {
        User user = userDao.find(form.getUsername());
        if (null == user) {
            result.rejectValue("username", "10", "学号不存在！");
        } else if (!user.validatePassword(form.getPassword())) {
            result.rejectValue("password", "11", "密码错误！");
        } else {
            UserIdentity.login(request, response, user, form.isRememberMe());
            userDao.updateLoginLocation(user, form.getLongitude(), form.getLatitude());
            return user;
        }
        return null;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        UserIdentity.logout(request, response);
    }

    @Override
    public void synchronizeUsers() {
        userDao.synchronizeUsers();
    }
}
