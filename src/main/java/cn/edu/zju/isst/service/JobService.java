package cn.edu.zju.isst.service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Job;

public interface JobService {
    public Job find(int id);
    public PaginationList<Job> findAll(String categoryAlias, String keywords, int pageSize, int page);
}