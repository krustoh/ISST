package cn.edu.zju.isst.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.dao.UserDao;
import cn.edu.zju.isst.entity.Klass;
import cn.edu.zju.isst.entity.Major;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.form.UserLoginForm;
import cn.edu.zju.isst.identity.UserIdentity;

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
            return user;
        }
        return null;
    }

    @Override
    public User logout(HttpServletRequest request, HttpServletResponse response) {
        return UserIdentity.logout(request, response);
    }

    @Override
    public User find(int id) {
        return userDao.find(id);
    }

    @Override
    public void updateLoginLocation(User user, double longitude, double latitude) {
        userDao.updateLoginLocation(user, longitude, latitude);
    }
    
    @Override
    public void synchronizeUsers() {
        userDao.synchronizeUsers();
    }

    @Override
    public List<Klass> findAllClasses() {
        return userDao.findAllClasses();
    }

    @Override
    public List<Major> findAllMajors() {
        return userDao.findAllMajors();
    }
}