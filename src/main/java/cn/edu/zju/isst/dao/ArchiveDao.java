package cn.edu.zju.isst.dao;

import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.ArchiveSearchCondition;

public interface ArchiveDao extends Dao<Archive> {
    public PaginationList<Archive> findAll(ArchiveSearchCondition condition, int pageSize, int page);
    public int changeStatus(Set<Integer> idset, int status);
}