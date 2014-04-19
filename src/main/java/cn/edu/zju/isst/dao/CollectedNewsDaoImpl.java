package cn.edu.zju.isst.dao;

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
    public PaginationList<CollectedNews> findAll(int categoryId, int published, int pageSize, int page) {
        SelectSQLBuilder select = select("*");
        
        select.where("category_id=:categoryId").addParam("categoryId", categoryId);
        
        if (0 == published) {
            select.where("post_id>0");
        } else {
            select.where("post_id=0");
        }
        
        return new PaginationList<CollectedNews>(page, pageSize, this, select);
    }
}