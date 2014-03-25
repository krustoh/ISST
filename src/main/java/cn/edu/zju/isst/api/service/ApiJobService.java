package cn.edu.zju.isst.api.service;

import java.util.List;

import cn.edu.zju.isst.entity.Job;

public interface ApiJobService {
    public List<Job> findAll(String categoryAlias, int pageSize, int page);
}
