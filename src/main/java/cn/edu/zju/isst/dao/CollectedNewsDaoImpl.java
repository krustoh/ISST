package cn.edu.zju.isst.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.CollectedNews;

@Repository
public class CollectedNewsDaoImpl extends AbstractDao<CollectedNews> implements CollectedNewsDao {
    
    public CollectedNewsDaoImpl() {
        super();
    }

    @Override
    public boolean hasCollected(int sourceId) {
        return jdbcTemplate.queryForObject("SELECT COUNT(id) FROM collected_news WHERE source_id=?", new Object[] {sourceId}, Integer.class) > 0 ? true : false;
    }

    @Override
    public List<CollectedNews> findAllUnpublished() {
        String sql = String.format("SELECT * FROM %s WHERE archive_id=0", table);
        return findAllBySql(sql);
    }
}