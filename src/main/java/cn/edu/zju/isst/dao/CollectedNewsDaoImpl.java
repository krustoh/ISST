package cn.edu.zju.isst.dao;

import java.util.Set;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.CollectedNews;

@Repository
public class CollectedNewsDaoImpl extends AbstractDao<CollectedNews> implements CollectedNewsDao {
    
    public CollectedNewsDaoImpl() {
        super();
    }

    @Override
    public boolean hasCollected(int sourceId) {
        return jdbcTemplate.getJdbcOperations().queryForObject("SELECT COUNT(id) FROM collected_news WHERE source_id=?", new Object[] {sourceId}, Integer.class) > 0 ? true : false;
    }

    @Override
    public PaginationList<CollectedNews> findAll(int categoryId, int status, int pageSize, int page) {
        SelectSQLBuilder select = select("*");
        
        select.where("category_id=:categoryId").addParam("categoryId", categoryId);
        
        if (status >= 0) {
            if (status == 1) {
                select.where("status<=:status").addParam("status", status);
            } else {
                select.where("status=:status").addParam("status", status);
            }
        }
        
        return new PaginationList<CollectedNews>(page, pageSize, this, select);
    }

    @Override
    public int ignore(Set<Integer> idset) {
        String sql = String.format("UPDATE %s SET status=:status WHERE %s IN (:idset)", table, primaryKey);
        
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("status", CollectedNews.STATUS_IGNORED);
        parameters.addValue("idset", idset);
        
        return jdbcTemplate.update(sql, parameters);
    }

    @Override
    public int unprocess(Set<Integer> idset) {
        String sql = String.format("UPDATE %s SET status=:status WHERE %s IN (:idset)", table, primaryKey);
        
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("status", CollectedNews.STATUS_UNPROCESSED);
        parameters.addValue("idset", idset);
        
        return jdbcTemplate.update(sql, parameters);
    }
}