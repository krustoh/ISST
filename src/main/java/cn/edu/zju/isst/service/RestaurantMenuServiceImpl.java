package cn.edu.zju.isst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.RestaurantMenuDao;
import cn.edu.zju.isst.entity.RestaurantMenu;

@Service
public class RestaurantMenuServiceImpl implements RestaurantMenuService {
    @Autowired
    private RestaurantMenuDao restaurantMenuDao;
    
    @Override
    public List<RestaurantMenu> findAll(int restaurantId) {
        return restaurantMenuDao.findAll(restaurantId);
    }
}