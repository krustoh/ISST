package cn.edu.zju.isst.api.service;

import java.util.List;

import cn.edu.zju.isst.entity.Archive;

public interface ApiArchiveService {
    public List<Archive> findAll(String categoryAlias, int pageSize, int page);
}
