package cn.edu.zju.isst.dao;

import java.sql.ResultSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Archive;

@Repository
public class ArchiveDaoImpl extends AbstractDao<Archive> implements ArchiveDao {
    @Autowired
    private StudentUserDao studentUserDao;
    
    @Override
    public PaginationList<Archive> findAll(int categoryId, int status, String keywords, int pageSize, int page) {
        SelectSQLBuilder select = select("id, category_id, user_id, title, description, updated_at, status")
                .where("category_id=:category_id")
                .addParam("category_id", categoryId)
                .orderBy("updated_at DESC, id DESC");
        
        if (status >= 0) {
            select.where("status=:status").addParam("status", status);
        }
        
        if (null != keywords) {
            keywords = keywords.trim();
            if (keywords.length() > 0) {
                for (String word : keywords.split(" ")) {
                    select.like("title", word);
                }
            }
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<Archive>(page, pageSize, this, select);
    }
    
    @Override
    protected void onFind(Archive entity, ResultSet rs, int rowNum) {
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