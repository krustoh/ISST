package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.entity.Job;

public interface JobService {
    public Job find(int id);
    public PaginationList<Job> findAll(String categoryAlias, String keywords, int pageSize, int page);
    public PaginationList<Job> findAll(Category category, int status, String keywords, int pageSize, int page);
    public Job save(Job job);
    public List<Job> findAll(Set<Integer> idset);
    public int delete(Job job);
    public int delete(Set<Integer> idset);
    public int publish(Set<Integer> idset);
    public int hide(Set<Integer> idset);
}