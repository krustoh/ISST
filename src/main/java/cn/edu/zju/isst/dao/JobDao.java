package cn.edu.zju.isst.dao;

import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.JobSearchCondition;

public interface JobDao extends Dao<Job> {
    public PaginationList<Job> findAll(JobSearchCondition condition, int pageSize, int page);
    public int changeStatus(Set<Integer> idset, int status);
}