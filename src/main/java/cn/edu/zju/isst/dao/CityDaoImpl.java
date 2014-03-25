package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.City;

@Repository
public class CityDaoImpl implements CityDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public City find(int id) {
        String sql = "SELECT * FROM cities WHERE id=?";
        List<City> list = jdbcTemplate.query(sql, new Integer[] { id }, BeanPropertyRowMapper.newInstance(City.class));
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
