package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Student;
import cn.edu.zju.isst.entity.StudentUser;

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

    @Override
    public String changePassword(int id, String password) {
        String encryptedPassword = StudentUser.encryptPassword(password);
        String sql = String.format("UPDATE %s SET password=? WHERE %s=?", table, primaryKey);
        jdbcTemplate.getJdbcOperations().update(sql, encryptedPassword, id);
        return encryptedPassword;
    }

    @Override
    public int findLike(String word) {
        SelectSQLBuilder select = select("id");
        select.like(word, "username", "name");
        Integer userId = jdbcTemplate.getJdbcOperations().queryForObject(select.toSQL(), Integer.class);
        return null == userId ? 0 : userId.intValue();
    }
}