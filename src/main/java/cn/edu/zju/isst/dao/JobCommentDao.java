package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.JobComment;

public interface JobCommentDao extends Dao<JobComment> {
    public PaginationList<JobComment> findAll(int jobId, int pageSize, int page);
}