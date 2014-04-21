package cn.edu.zju.isst.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;

@Repository
public class ActivityDaoImpl extends AbstractDao<Activity> implements ActivityDao {
    @Autowired
    private StudentUserDao studentUserDao;
    
    @Override
    public PaginationList<Activity> findAll(ActivitySearchCondition condition, int pageSize, int page) {
        SelectSQLBuilder select = select("a.id, a.title, a.location, a.city_id, a.user_id, a.status, a.picture_path, a.description, a.start_time, a.expire_time, a.updated_at", "a")
                .leftJoin("city c", "c.id=a.city_id", "c.name city_name")
                .orderBy("a.updated_at DESC");
        
        parseSearchCondition(condition, select);
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<Activity>(page, pageSize, this, select);
    }
    
    protected void onFindMissColumn(Activity entity, String column, ResultSet rs, int rowNum) throws SQLException {
        if ("city_name".equals(column)) {
            entity.setCityName(rs.getString("city_name"));
        }
    }
    
    private void parseSearchCondition(ActivitySearchCondition condition, SelectSQLBuilder select) {
        if (condition.getCityId() >= 0) {
            select.where("city_id=:cityId").addParam("cityId", condition.getCityId());
        }
        
        if (condition.getUserId() >= 0) {
            select.where("user_id=:userId").addParam("userId", condition.getUserId());
        }
        
        if (condition.getStatus() >= 0) {
            select.where("status=:status").addParam("status", condition.getStatus());
        }
        
        if (null != condition.getKeywords()) {
            String keywords = condition.getKeywords().trim();
            if (keywords.length() > 0) {
                for (String word : keywords.split(" ")) {
                    select.like(word, "title", "location", "description");
                }
            }
        }
    }
    
    @Override
    protected void onFind(Activity entity, ResultSet rs, int rowNum) {
        if (entity.getUserId() > 0) {
            entity.setUser(studentUserDao.findUserSummary(entity.getUserId()));
        }
    }

    @Override
    public int changeStatus(Set<Integer> idset, int status) {
        String sql = String.format("UPDATE %s SET status=:status WHERE %s IN (:idset)", table, primaryKey);
        
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idset", idset);
        paramSource.addValue("status", status);
        
        return jdbcTemplate.update(sql, paramSource);
    }
}