package cn.edu.zju.isst.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Klass;
import cn.edu.zju.isst.entity.Major;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.entity.UserSummary;
import cn.edu.zju.isst.form.UserLoginForm;

public interface UserService {
    public StudentUser find(int id);
    public StudentUser find(String username);
    public StudentUser findAlumnus(int id);
    public StudentUser login(HttpServletRequest request, HttpServletResponse response, UserLoginForm form, BindingResult result);
    public void updateLoginLocation(StudentUser user, double longitude, double latitude);
    public StudentUser logout(HttpServletRequest request, HttpServletResponse response);
    public List<Klass> findAllClasses();
    public List<Major> findAllMajors();
    public PaginationList<StudentUser> findAll(UserSearchCondition condition, int pageSize, int page);
    public PaginationList<StudentUser> findAlumni(UserSearchCondition condition, int pageSize, int page);
    public UserSummary findUserSummary(int id);
    public boolean checkUsername(String username, int id);
    public boolean checkUsername(String username);
    public void save(StudentUser user);
    public Klass findClass(int id);
    public Major findMajor(int id);
    public String changePassword(int id, String password);
    public int findLike(String word);
}