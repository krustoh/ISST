package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.dao.CityDao;
import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.CitySearchCondition;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;
    
    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    public List<City> findAllForSelect() {
        SelectSQLBuilder select = cityDao.select("id, name")
            .where("status=:status")
            .addParam("status", City.STATUS_PUBLISHED)
            .orderBy("id ASC");
        
        return cityDao.queryAll(select);
    }

    @Override
    public City find(int id) {
        return cityDao.find(id);
    }

    @Override
    public PaginationList<City> findAll(CitySearchCondition condition, int pageSize, int page) {
        return cityDao.findAll(condition, pageSize, page);
    }

    @Override
    public void save(City city) {
        cityDao.save(city);
    }

    @Override
    public List<City> findAll(Set<Integer> idset) {
        return cityDao.findAll(idset);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return cityDao.delete(idset);
    }

    @Override
    public int delete(City city) {
        return cityDao.delete(city);
    }

    @Override
    public int hide(Set<Integer> idset) {
        return cityDao.changeStatus(idset, City.STATUS_HIDDEN);
    }

    @Override
    public int publish(Set<Integer> idset) {
        return cityDao.changeStatus(idset, City.STATUS_PUBLISHED);
    }
}