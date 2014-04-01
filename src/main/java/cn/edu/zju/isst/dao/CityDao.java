package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.entity.City;

public interface CityDao extends Dao<City> {
    public City find(int id);
}