package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.entity.JobSearchCondition;

public interface JobService {
    public Job find(int id);
    public PaginationList<Job> findAll(JobSearchCondition condition, int pageSize, int page);
    public Job save(Job job);
    public List<Job> findAll(Set<Integer> idset);
    public int delete(Job job);
    public int delete(Set<Integer> idset);
    public int publish(Set<Integer> idset);
    public int hide(Set<Integer> idset);
}