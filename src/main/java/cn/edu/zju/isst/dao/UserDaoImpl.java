package cn.edu.zju.isst.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.Klass;
import cn.edu.zju.isst.entity.Major;
import cn.edu.zju.isst.entity.User;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    
    @Override
    public void updateLoginLocation(int id, double longitude, double latitude) {
        String sql = "REPLACE INTO user_locations SET user_id=?, longitude=?, latitude=?, login_time=?";
        jdbcTemplate.getJdbcOperations().update(sql, new Object[] { id, longitude, latitude, new Timestamp(System.currentTimeMillis()) });
    }
    
    @Override
    public List<Klass> findAllClasses() {
        return queryAll("SELECT id, name FROM classes ORDER BY name ASC", ParameterizedBeanPropertyRowMapper.newInstance(Klass.class));
    }

    @Override
    public List<Major> findAllMajors() {
        return queryAll("SELECT id, name FROM class_major_fields ORDER BY name ASC", ParameterizedBeanPropertyRowMapper.newInstance(Major.class));
    }

    @Override
    public Klass findClass(int id) {
        return query("SELECT id, name FROM classes WHERE id=?", ParameterizedBeanPropertyRowMapper.newInstance(Klass.class), id);
    }

    @Override
    public Major findMajor(int id) {
        return query("SELECT id, name FROM class_major_fields WHERE id=?", ParameterizedBeanPropertyRowMapper.newInstance(Major.class), id);
    }

    @Override
    public int updateCityPrincipal(int userId, boolean isCityPrincipal) {
        String sql = "UPDATE users SET city_principal=? WHERE id=?";
        return jdbcTemplate.getJdbcOperations().update(sql, isCityPrincipal ? 1 : 0, userId);
    }
}