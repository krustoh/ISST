package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.RestaurantMenu;

@Repository
public class RestaurantMenuDaoImpl extends AbstractDao<RestaurantMenu> implements RestaurantMenuDao {
    @Override
    public PaginationList<RestaurantMenu> findAll(int restaurantId, int pageSize, int page) {
        SelectSQLBuilder select = select("*");
        
        select.where("restaurant_id=:restaurantId").addParam("restaurantId", restaurantId);
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<RestaurantMenu>(page, pageSize, this, select);
    }
}