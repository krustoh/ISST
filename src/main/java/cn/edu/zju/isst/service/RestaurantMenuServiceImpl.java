package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.RestaurantMenuDao;
import cn.edu.zju.isst.entity.RestaurantMenu;

@Service
public class RestaurantMenuServiceImpl implements RestaurantMenuService {
    @Autowired
    private RestaurantMenuDao restaurantMenuDao;
    
    @Override
    public PaginationList<RestaurantMenu> findAll(int restaurantId, int pageSize, int page) {
        return restaurantMenuDao.findAll(restaurantId, pageSize, page);
    }

    @Override
    public RestaurantMenu find(int id) {
        return restaurantMenuDao.find(id);
    }

    @Override
    public void save(RestaurantMenu restaurantMenu) {
        restaurantMenuDao.save(restaurantMenu);
    }

    @Override
    public List<RestaurantMenu> findAll(Set<Integer> idset) {
        return restaurantMenuDao.findAll(idset);
    }

    @Override
    public int delete(RestaurantMenu restaurantMenu) {
        return restaurantMenuDao.delete(restaurantMenu);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return restaurantMenuDao.delete(idset);
    }
}