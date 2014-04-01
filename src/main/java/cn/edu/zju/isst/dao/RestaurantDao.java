package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.Restaurant;

public interface RestaurantDao extends Dao<Restaurant> {
    public List<Restaurant> findAll(String keywords, int pageSize, int page);
}