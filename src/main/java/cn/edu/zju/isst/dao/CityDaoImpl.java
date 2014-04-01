package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.City;

@Repository
public class CityDaoImpl extends AbstractDao<City> implements CityDao {
    @Autowired
    private UserDao userDao;
    
    @Override
    public List<City> findAll() {
        return queryAll(String.format("SELECT * FROM %s WHERE status=? ORDER BY id ASC", table), City.STATUS_PUBLISHED);
    }
    
    @Override
    protected void onFind(City entity) {
        if (entity.getUserId() > 0) {
            entity.setUser(userDao.findUserSummary(entity.getUserId()));
        }
    }
}