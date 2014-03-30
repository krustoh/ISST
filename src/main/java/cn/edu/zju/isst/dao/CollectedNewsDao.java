package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.CollectedNews;

public interface CollectedNewsDao extends Dao<CollectedNews> {
    public void insert(CollectedNews entity);
    public boolean hasCollected(int sourceId);
    public List<CollectedNews> findAllUnpublished();
}