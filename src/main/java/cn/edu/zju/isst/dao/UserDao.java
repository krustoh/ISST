package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.Klass;
import cn.edu.zju.isst.entity.Major;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.entity.UserSummary;

public interface UserDao extends Dao<User> {
    public User find(String username);
    public void updateLoginLocation(User user, double longitude, double latitude);
    public void synchronizeUsers();
    public UserSummary findUserSummary(int id);
    public List<Klass> findAllClasses();
    public List<Major> findAllMajors();
}