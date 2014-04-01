package cn.edu.zju.isst.service;

import java.util.List;

import cn.edu.zju.isst.entity.Restaurant;

public interface RestaurantService {
    public Restaurant find(int id);
    public List<Restaurant> findAll(String keywords, int pageSize, int page);
}