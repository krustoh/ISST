package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.CollectedNews;

public interface CollectedNewsDao extends Dao<CollectedNews> {
    public void insert(CollectedNews entity);
    public boolean hasCollected(int sourceId);
    public PaginationList<CollectedNews> findAll(int categoryId, int published, int pageSize, int page);
}