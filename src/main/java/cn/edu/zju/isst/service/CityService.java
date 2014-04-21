package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.CitySearchCondition;

public interface CityService {
    public City find(int id);
    public List<City> findAll();
    public List<City> findAllForSelect();
    public PaginationList<City> findAll(CitySearchCondition condition, int pageSize, int page);
    public void save(City city);
    public List<City> findAll(Set<Integer> idset);
    public int delete(Set<Integer> idset);
    public int delete(City city);
    public int hide(Set<Integer> idset);
    public int publish(Set<Integer> idset);
}