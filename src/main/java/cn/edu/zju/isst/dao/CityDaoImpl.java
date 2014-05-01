package cn.edu.zju.isst.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.CitySearchCondition;

@Repository
public class CityDaoImpl extends AbstractDao<City> implements CityDao {
    @Autowired
    private StudentUserDao studentUserDao;
    
    @Override
    public List<City> findAll() {
        return queryAll(String.format("SELECT * FROM %s WHERE status=? ORDER BY id ASC", table), City.STATUS_PUBLISHED);
    }
    
    @Override
    protected void onFind(City entity, ResultSet rs, int rowNum) {
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
    public PaginationList<City> findAll(CitySearchCondition condition, int pageSize, int page) {
        SelectSQLBuilder select = select("*");
        
        if (condition.getStatus() >=0 ) {
            select.where("status=:status").addParam("status", condition.getStatus());
        }
        
        if (null != condition.getKeywords()) {
            String keywords = condition.getKeywords().trim();
            if (keywords.length() > 0) {
                for (String word : keywords.split(" ")) {
                    select.like("name", word);
                }
            }
        }
        
        return new PaginationList<City>(page, pageSize, this, select);
    }
}