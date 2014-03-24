package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.Job;

public interface JobDao {
    public Job find(int id);
    public List<Job> findAll(int categoryId, int status, int pageSize, int page);
}
