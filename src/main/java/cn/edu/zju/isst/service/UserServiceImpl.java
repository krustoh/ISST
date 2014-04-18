package cn.edu.zju.isst.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.StudentDao;
import cn.edu.zju.isst.dao.StudentUserDao;
import cn.edu.zju.isst.dao.UserDao;
import cn.edu.zju.isst.entity.Klass;
import cn.edu.zju.isst.entity.Major;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.form.UserLoginForm;
import cn.edu.zju.isst.identity.UserIdentity;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private StudentUserDao studentUserDao;
    
    @Autowired
    private StudentDao studentDao;
    
    @Override
    public StudentUser login(HttpServletRequest request, HttpServletResponse response, UserLoginForm form, BindingResult result) {
        StudentUser user = studentUserDao.find(form.getUsername());
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
    public StudentUser logout(HttpServletRequest request, HttpServletResponse response) {
        return UserIdentity.logout(request, response);
    }

    @Override
    public StudentUser find(int id) {
        return studentUserDao.find(id);
    }

    @Override
    public void updateLoginLocation(StudentUser user, double longitude, double latitude) {
        userDao.updateLoginLocation(user.getId(), longitude, latitude);
    }

    @Override
    public List<Klass> findAllClasses() {
        return userDao.findAllClasses();
    }

    @Override
    public List<Major> findAllMajors() {
        return userDao.findAllMajors();
    }

    @Override
    public PaginationList<StudentUser> findAll(UserSearchCondition condition, int pageSize, int page) {
        return studentUserDao.findAll(condition, pageSize, page);
    }

    @Override
    public PaginationList<StudentUser> findAlumni(UserSearchCondition condition, int pageSize, int page) {
        return studentUserDao.findAlumni(condition, pageSize, page);
    }

    @Override
    public StudentUser findAlumnus(int id) {
        return studentUserDao.findAlumnus(id);
    }

    @Override
    public boolean checkUsername(String username, int id) {
        return studentDao.checkUsername(username, id);
    }

    @Override
    public boolean checkUsername(String username) {
        return studentDao.checkUsername(username);
    }
    
    public void save(StudentUser user) {
        studentUserDao.save(user);
    }

    @Override
    public Klass findClass(int id) {
        return userDao.findClass(id);
    }

    @Override
    public Major findMajor(int id) {
        return userDao.findMajor(id);
    }
}