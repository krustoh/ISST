package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.City;

public interface CityDao extends Dao<City> {
    public List<City> findAll();
}