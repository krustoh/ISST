package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.RestaurantMenu;

public interface RestaurantMenuDao extends Dao<RestaurantMenu> {
    public List<RestaurantMenu> findAll(int restaurantId);
}