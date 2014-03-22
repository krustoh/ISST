package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.entity.User;

public interface UserDao {
    public User find(int id);
    public User find(String username);
    public void updateLoginLocation(User user, double longitude, double latitude);
    public void synchronizeUsers();
}
