package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.RestaurantMenu;

@Repository
public class RestaurantMenuDaoImpl extends AbstractDao<RestaurantMenu> implements RestaurantMenuDao {
    @Override
    public List<RestaurantMenu> findAll(int restaurantId) {
        return queryAll(String.format("SELECT * FROM %s WHERE restaurant_id=? ORDER BY id ASC", table), restaurantId);
    }
}