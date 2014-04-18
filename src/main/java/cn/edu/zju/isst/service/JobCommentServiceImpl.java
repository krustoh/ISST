package cn.edu.zju.isst.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.JobCommentDao;
import cn.edu.zju.isst.entity.JobComment;

@Service
public class JobCommentServiceImpl implements JobCommentService {
    @Autowired
    private JobCommentDao jobCommentDao;
    
    @Override
    public PaginationList<JobComment> findAll(int jobId, int pageSize, int page) {
        return jobCommentDao.findAll(jobId, pageSize, page);
    }

    @Override
    public void save(JobComment jobComment) {
        jobComment.setCreatedAt(new Date());
        jobCommentDao.save(jobComment);
    }

    @Override
    public List<JobComment> findAll(Set<Integer> idset) {
        return jobCommentDao.findAll(idset);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return jobCommentDao.delete(idset);
    }

    @Override
    public int delete(JobComment jobComment) {
        return jobCommentDao.delete(jobComment);
    }

    @Override
    public JobComment find(int id) {
        return jobCommentDao.find(id);
    }
}