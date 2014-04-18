package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.JobComment;

public interface JobCommentService {
    public PaginationList<JobComment> findAll(int jobId, int pageSize, int page);
    public void save(JobComment jobComment);
    public JobComment find(int id);
    public List<JobComment> findAll(Set<Integer> idset);
    public int delete(Set<Integer> idset);
    public int delete(JobComment jobComment);
}