package cn.edu.zju.isst.dao;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.CitySearchCondition;

public interface CityDao extends Dao<City> {
    public List<City> findAll();
    public PaginationList<City> findAll(CitySearchCondition condition, int pageSize, int page);
    public int changeStatus(Set<Integer> idset, int status);
}