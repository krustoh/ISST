package cn.edu.zju.isst.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.JobSearchCondition;

@Repository
public class JobDaoImpl extends AbstractDao<Job> implements JobDao {
    @Autowired
    private StudentUserDao studentUserDao;

    @Override
    public PaginationList<Job> findAll(JobSearchCondition condition, int pageSize, int page) {
        SelectSQLBuilder select = select("j.id, j.category_id, j.user_id, j.city_id, j.title, j.company, j.position, j.updated_at, j.status, j.description", "j")
                .leftJoin("cities c", "c.id=j.city_id", "c.name city_name")
                .orderBy("j.updated_at DESC, j.id DESC");
        
        if (condition.getCategoryId() > 0) {
            select.where("j.category_id=:category_id").addParam("category_id", condition.getCategoryId());
        }
        
        if (condition.getUserId() > 0) {
            select.where("j.user_id=:user_id").addParam("user_id", condition.getUserId());
        }
        
        if (condition.getStatus() >= 0) {
            select.where("j.status=:status").addParam("status", condition.getStatus());
        }
        
        if (null != condition.getKeywords()) {
            String keywords = condition.getKeywords().trim();
            for (String word : keywords.split(" ")) {
                if (keywords.length() > 0) {
                    select.like(word, "j.title", "j.company", "j.position");
                }
            }
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<Job>(page, pageSize, this, select);
    }
    
    @Override
    protected void onFind(Job entity, ResultSet rs, int rowNum) {
        if (entity.getUserId() > 0) {
            entity.setUser(studentUserDao.findUserSummary(entity.getUserId()));
        }
    }
    
    @Override
    protected void onFindMissColumn(Job entity, String column, ResultSet rs, int rowNum) throws SQLException {
        if ("city_name".equals(column)) {
            entity.setCityName(rs.getString(column));
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