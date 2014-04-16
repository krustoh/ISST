package cn.edu.zju.isst.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.CategoryDao;
import cn.edu.zju.isst.dao.JobDao;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.entity.Job;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Job find(int id) {
        return jobDao.find(id);
    }
    
    @Override
    public PaginationList<Job> findAll(String categoryAlias, String keywords, int pageSize, int page) {
        return findAll(categoryDao.find(categoryAlias), Job.STATUS_PUBLISHED, keywords, pageSize, page);
    }

    @Override
    public PaginationList<Job> findAll(Category category, int status, String keywords, int pageSize, int page) {
        if (null != category) {
            return jobDao.findAll(category.getId(), keywords, Job.STATUS_PUBLISHED, pageSize, page);
        } else {
            return new PaginationList<Job>(page, pageSize);
        }
    }

    @Override
    public Job save(Job job) {
        job.setUpdatedAt(new Date());
        job.setDescriptionFromContent(job.getContent());
        jobDao.save(job);
        
        return job;
    }

    @Override
    public List<Job> findAll(Set<Integer> idset) {
        return jobDao.findAll(idset);
    }

    @Override
    public int delete(Job job) {
        return jobDao.delete(job);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return jobDao.delete(idset);
    }

    @Override
    public int publish(Set<Integer> idset) {
        return jobDao.changeStatus(idset, Job.STATUS_PUBLISHED);
    }

    @Override
    public int hide(Set<Integer> idset) {
        return jobDao.changeStatus(idset, Job.STATUS_HIDDEN);
    }
}