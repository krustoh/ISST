package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Restaurant;

public interface RestaurantDao extends Dao<Restaurant> {
    public PaginationList<Restaurant> findAll(String keywords, int pageSize, int page);
}