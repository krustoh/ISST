package cn.edu.zju.isst.dao;

import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.CollectedNews;

public interface CollectedNewsDao extends Dao<CollectedNews> {
    public void insert(CollectedNews entity);
    public boolean hasCollected(int sourceId);
    public PaginationList<CollectedNews> findAll(int categoryId, int status, int pageSize, int page);
    public int ignore(Set<Integer> idset);
    public int unprocess(Set<Integer> idset);
}