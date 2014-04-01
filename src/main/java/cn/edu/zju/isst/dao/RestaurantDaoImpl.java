package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Restaurant;

@Repository
public class RestaurantDaoImpl extends AbstractDao<Restaurant> implements RestaurantDao {
    @Override
    public List<Restaurant> findAll(String keywords, int pageSize, int page) {
        SelectSQLBuilder select = select("id, name, address, hotline, business_hours, description");
        
        if (null != keywords) {
            keywords = keywords.trim();
            if (keywords.length() > 0) {
                select.like("name", keywords);
            }
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return queryAll(select);
    }
}