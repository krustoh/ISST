package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Archive;

public interface ArchiveDao extends Dao<Archive> {
    public PaginationList<Archive> findAll(int categoryId, String keywords, int status, int pageSize, int page);
}