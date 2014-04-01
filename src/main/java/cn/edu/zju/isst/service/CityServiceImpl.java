package cn.edu.zju.isst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.CityDao;
import cn.edu.zju.isst.entity.City;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;
    
    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }
}