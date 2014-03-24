package cn.edu.zju.isst.service;

import java.util.List;

import cn.edu.zju.isst.entity.Job;

public interface JobService {
    public List<Job> findAll(String categoryAlias, int pageSize, int page);
    public Job find(int id);
}