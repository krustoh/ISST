package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.Archive;

public interface ArchiveDao extends Dao<Archive> {
    public List<Archive> findAll(int categoryId, String keywords, int status, int pageSize, int page);
}