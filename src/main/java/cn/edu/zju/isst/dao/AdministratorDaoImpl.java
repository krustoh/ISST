package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.Administrator;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Administrator find(String username) {
        String sql = "SELECT * FROM admins WHERE username=?";
        List<Administrator> list = jdbcTemplate.query(sql, new String[] { username }, ParameterizedBeanPropertyRowMapper.newInstance(Administrator.class));
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Administrator find(int id) {
        String sql = "SELECT * FROM admins WHERE id=?";
        List<Administrator> list = jdbcTemplate.query(sql, new Integer[] { id }, ParameterizedBeanPropertyRowMapper.newInstance(Administrator.class));
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
