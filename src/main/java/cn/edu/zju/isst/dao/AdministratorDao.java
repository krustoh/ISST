package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.Administrator;

@Repository
public interface AdministratorDao {
    public Administrator find(String username);
    public Administrator find(int id);
}
