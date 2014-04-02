package cn.edu.zju.isst.service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Archive;

public interface ArchiveService {
    public Archive find(int id);
    public PaginationList<Archive> findAll(String categoryAlias, String keywords, int pageSize, int page);
}