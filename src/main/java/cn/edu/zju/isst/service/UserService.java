package cn.edu.zju.isst.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Klass;
import cn.edu.zju.isst.entity.Major;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.form.UserLoginForm;

public interface UserService {
    public User find(int id);
    public User findAlumnus(int id);
    public User login(HttpServletRequest request, HttpServletResponse response, UserLoginForm form, BindingResult result);
    public void updateLoginLocation(User user, double longitude, double latitude);
    public User logout(HttpServletRequest request, HttpServletResponse response);
    public void synchronizeUsers();
    public List<Klass> findAllClasses();
    public List<Major> findAllMajors();
    public PaginationList<User> findAll(UserSearchCondition condition, int pageSize, int page);
    public List<User> findAlumni(UserSearchCondition condition, int pageSize, int page);
}