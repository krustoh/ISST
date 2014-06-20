package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.ArchiveSearchCondition;
public interface ArchiveService {
    public Archive save(Archive archive);
    public Archive find(int id);
    public List<Archive> findAll(Set<Integer> ids);
    public PaginationList<Archive> findAll(ArchiveSearchCondition condition, int pageSize, int page);
    public int delete(Set<Integer> idset);
    public void delete(Archive archive);
    public int publish(Set<Integer> idset);
    public int hide(Set<Integer> idset);
}