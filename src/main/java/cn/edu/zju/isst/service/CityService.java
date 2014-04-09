package cn.edu.zju.isst.service;

import java.util.List;

import cn.edu.zju.isst.entity.City;

public interface CityService {
    public City find(int id);
    public List<City> findAll();
    public List<City> findAllForSelect();
}