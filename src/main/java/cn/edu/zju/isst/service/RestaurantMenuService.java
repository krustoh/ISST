package cn.edu.zju.isst.service;

import java.util.List;

import cn.edu.zju.isst.entity.RestaurantMenu;

public interface RestaurantMenuService {
    public List<RestaurantMenu> findAll(int restaurantId);
}