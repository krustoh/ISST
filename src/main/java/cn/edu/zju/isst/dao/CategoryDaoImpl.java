package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Category find(int id) {
        String sql = "SELECT * FROM categories WHERE id=?";
        
        List<Category> list = jdbcTemplate.query(sql, new Object[] { id}, BeanPropertyRowMapper.newInstance(Category.class));
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }

    @Override
    public Category find(String alias) {
        String sql = "SELECT * FROM categories WHERE alias=?";
        
        List<Category> list = jdbcTemplate.query(sql, new Object[] { alias}, BeanPropertyRowMapper.newInstance(Category.class));
        if (list.isEmpty()) {
            return null;
        }
        
        return list.get(0);
    }

}
