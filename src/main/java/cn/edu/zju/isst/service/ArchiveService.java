package cn.edu.zju.isst.service;

import java.util.List;

import cn.edu.zju.isst.entity.Archive;

public interface ArchiveService {
    public List<Archive> findAll(String categoryAlias, int pageSize, int page);
    public Archive find(int id);
}
