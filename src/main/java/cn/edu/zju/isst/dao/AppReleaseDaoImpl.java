package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.AppRelease;

@Repository
public class AppReleaseDaoImpl extends AbstractDao<AppRelease> implements AppReleaseDao {
    
    public List<AppRelease> findAll() {
        String sql = "SELECT * FROM app_release ORDER BY build DESC";
        return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(AppRelease.class));
    }

    public AppRelease getLatestVersion() {
        String sql = "SELECT * FROM app_release ORDER BY build DESC LIMIT 1";
        List<AppRelease> apps = jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(AppRelease.class));
        if (apps.isEmpty()) {
            return null;
        }

        return apps.get(0);
    }
}
