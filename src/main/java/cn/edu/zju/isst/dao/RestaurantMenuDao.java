package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.RestaurantMenu;

public interface RestaurantMenuDao extends Dao<RestaurantMenu> {
    public PaginationList<RestaurantMenu> findAll(int restaurantId, int pageSize, int page);
}