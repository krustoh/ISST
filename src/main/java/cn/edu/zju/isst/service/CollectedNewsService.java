package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.CollectedNews;

public interface CollectedNewsService {
    public CollectedNews find(int id);
    public PaginationList<CollectedNews> findAll(int categoryId, int status, int pageSize, int page);
    public List<CollectedNews> findAll(Set<Integer> idset);
    public int delete(CollectedNews news);
    public int delete(Set<Integer> idset);
    public void save(CollectedNews news);
    public List<CollectedNews> collectForArchive(int categoryId);
    public List<CollectedNews> collectForJob(int categoryId);
    public CollectedNews collectDetail(int id);
    public int publishForArchive(Set<Integer> idset);
    public int publishForJob(Set<Integer> idset);
    public int ignore(Set<Integer> idset);
    public int unprocess(Set<Integer> idset);
}