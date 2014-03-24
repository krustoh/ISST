package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.Archive;

public interface ArchiveDao {
    public Archive find(int id);
    public List<Archive> findAll(int categoryId, int status, int pageSize, int page);
}