package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Restaurant;

public interface RestaurantService {
    public Restaurant find(int id);
    public PaginationList<Restaurant> findAll(String keywords, int pageSize, int page);
    public List<Restaurant> findAll(Set<Integer> idset);
    public void save(Restaurant restaurant);
    public int delete(Restaurant restaurant);
    public int delete(Set<Integer> idset);
}