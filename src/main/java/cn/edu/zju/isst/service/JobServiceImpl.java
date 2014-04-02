package cn.edu.zju.isst.service;

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
        Category category = categoryDao.find(categoryAlias);
        if (null != category) {
            return jobDao.findAll(category.getId(), keywords, Job.STATUS_PUBLISHED, pageSize, page);
        } else {
            return new PaginationList<Job>(page, pageSize);
        }
    }
}