package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Activity;

@Repository
public class ActivityDaoImpl extends AbstractDao<Activity> implements ActivityDao {
    @Override
    public List<Activity> findAllOfCampus(String keywords, int pageSize, int page) {
        SelectSQLBuilder select = select("id, title, picture, description")
                .where("city_id=0 AND user_id=0 AND status=:status").addParam("status", Activity.STATUS_PUBLISHED)
                .orderBy("updated_at DESC");
        
        if (null != keywords) {
            keywords = keywords.trim();
            if (keywords.length() > 0) {
                select.like("title", keywords);
            }
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return queryAll(select);
    }
}