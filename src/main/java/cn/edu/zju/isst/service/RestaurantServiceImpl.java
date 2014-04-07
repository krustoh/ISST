package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.RestaurantDao;
import cn.edu.zju.isst.entity.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantDao restaurantDao;
    
    @Override
    public Restaurant find(int id) {
        return restaurantDao.find(id);
    }

    @Override
    public PaginationList<Restaurant> findAll(String keywords, int pageSize, int page) {
        return restaurantDao.findAll(keywords, pageSize, page);
    }

    @Override
    public void save(Restaurant restaurant) {
        restaurantDao.save(restaurant);
    }

    @Override
    public List<Restaurant> findAll(Set<Integer> idset) {
        return restaurantDao.findAll(idset);
    }

    @Override
    public int delete(Restaurant restaurant) {
        return restaurantDao.delete(restaurant);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return restaurantDao.delete(idset);
    }
}