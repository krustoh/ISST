package cn.edu.zju.isst.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;
import cn.edu.zju.isst.entity.StudentUser;

@Repository
public class ActivityDaoImpl extends AbstractDao<Activity> implements ActivityDao {
    @Autowired
    private StudentUserDao studentUserDao;
    
    @Override
    public PaginationList<Activity> findAll(ActivitySearchCondition condition, int pageSize, int page) {
        SelectSQLBuilder select = select("a.id, a.title, a.location, a.city_id, a.user_id, a.status, a.picture_path, a.description, a.start_time, a.expire_time, a.updated_at", "a")
                .leftJoin("cities c", "c.id=a.city_id", "c.name city_name")
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
            select.where("a.city_id=:cityId").addParam("cityId", condition.getCityId());
        }
        
        if (condition.getUserId() >= 0) {
            select.where("a.user_id=:userId").addParam("userId", condition.getUserId());
        } else if (null != condition.getPoster()) {
            String poster = condition.getPoster().trim();
            if (poster.length() > 0) {
                select.leftJoin("students s", "s.id=a.user_id");
                select.like(poster, "s.username", "s.name");
            }
        }
        
        if (condition.getStatus() >= 0) {
            select.where("a.status=:status").addParam("status", condition.getStatus());
        }
        
        if (null != condition.getKeywords()) {
            String keywords = condition.getKeywords().trim();
            if (keywords.length() > 0) {
                for (String word : keywords.split(" ")) {
                    select.like(word, "a.title", "a.location", "a.description");
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

    @Override
    public boolean participate(int activityId, int userId) {
        return participate(activityId, userId, new Date());
    }
    
    @Override
    public boolean unparticipate(int activityId, int userId) {
        String sql = "DELETE FROM activity_participants WHERE activity_id=? AND user_id=?";
        return jdbcTemplate.getJdbcOperations().update(sql, activityId, userId) > 0 ? true : false;
    }

    @Override
    public boolean hasParticipated(int activityId, int userId) {
        String sql = "SELECT COUNT(*) FROM activity_participants WHERE activity_id=? AND user_id=?";
        return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class, activityId, userId).intValue() > 0 ? true : false;
    }

    @Override
    public boolean participate(int activityId, int userId, Date createdAt) {
        if (hasParticipated(activityId, userId)) {
            return false;
        }
        
        String sql = "INSERT INTO activity_participants (activity_id, user_id, created_at) VALUES (?, ?, ?)";
        jdbcTemplate.getJdbcOperations().update(sql, activityId, userId, createdAt);
        
        return true;
    }

    @Override
    public int changeStatus(int id, int status) {
        String sql = String.format("UPDATE %s SET status=? WHERE %s=?", table, primaryKey);    
        return jdbcTemplate.getJdbcOperations().update(sql, status, id);
    }

    @Override
    public PaginationList<Activity> findUserParticipatedList(int userId, int pageSize, int page) {
        SelectSQLBuilder select = selectFrom("activity_participants", null, "ap")
                .select("a.id, a.title, a.location, a.city_id, a.user_id, a.status, a.picture_path, a.description, a.start_time, a.expire_time, a.updated_at")
                .leftJoin("activities a", "a.id=ap.activity_id")
                .leftJoin("cities c", "c.id=a.city_id", "c.name city_name")
                .where("ap.user_id=:user_id").addParam("user_id", userId)
                .orderBy("a.updated_at DESC");
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<Activity>(page, pageSize, this, select);
    }
}