package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.Klass;
import cn.edu.zju.isst.entity.Major;
import cn.edu.zju.isst.entity.User;

public interface UserDao extends Dao<User> {
    public void updateLoginLocation(int id, double longitude, double latitude);
    public int updateCityPrincipal(int userId, boolean isCityPrincipal);
    public List<Klass> findAllClasses();
    public Klass findClass(int id);
    public List<Major> findAllMajors();
    public Major findMajor(int id);
}