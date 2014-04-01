package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.Job;

public interface JobDao extends Dao<Job> {
    public List<Job> findAll(int categoryId, String keywords, int status, int pageSize, int page);
}