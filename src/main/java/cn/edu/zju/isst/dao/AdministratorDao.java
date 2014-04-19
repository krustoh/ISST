package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.Administrator;

@Repository
public interface AdministratorDao extends Dao<Administrator> {
    public Administrator find(String username);
    public void changePassword(int id, String password);
}