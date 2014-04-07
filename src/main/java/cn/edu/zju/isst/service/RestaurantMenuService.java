package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.RestaurantMenu;

public interface RestaurantMenuService {
    public void save(RestaurantMenu restaurantMenu);
    public RestaurantMenu find(int id);
    public List<RestaurantMenu> findAll(Set<Integer> idset);
    public PaginationList<RestaurantMenu> findAll(int restaurantId, int pageSize, int page);
    public int delete(RestaurantMenu restaurantMenu);
    public int delete(Set<Integer> idset);
}