package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Restaurant;

@Repository
public class RestaurantDaoImpl extends AbstractDao<Restaurant> implements RestaurantDao {
    @Override
    public PaginationList<Restaurant> findAll(String keywords, int pageSize, int page) {
        SelectSQLBuilder select = select("id, name, picture, address, hotline, business_hours, content");
        
        if (null != keywords) {
            keywords = keywords.trim();
            if (keywords.length() > 0) {
                select.like("name", keywords);
            }
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<Restaurant>(page, pageSize, this, select);
    }
}