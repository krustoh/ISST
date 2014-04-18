package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Student;

@Service
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao {
    
    @Override
    public boolean checkUsername(String username, int id) {
        SelectSQLBuilder select = select().where("username=:username").addParam("username", username);
        if (id > 0) {
            select.where("id<>:id").addParam("id", id);
        }
        
        return count(select) > 0;
    }
    
    @Override
    public boolean checkUsername(String username) {
        return checkUsername(username, 0);
    }
}