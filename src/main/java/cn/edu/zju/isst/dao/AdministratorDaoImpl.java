package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.Administrator;

@Repository
public class AdministratorDaoImpl extends AbstractDao<Administrator> implements AdministratorDao {
    
    @Override
    public Administrator find(String username) {
        return query("SELECT * FROM admins WHERE username=?", username);
    }
}