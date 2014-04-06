package cn.edu.zju.isst.service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Restaurant;

public interface RestaurantService {
    public Restaurant find(int id);
    public PaginationList<Restaurant> findAll(String keywords, int pageSize, int page);
}