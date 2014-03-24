package cn.edu.zju.isst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.CategoryDao;
import cn.edu.zju.isst.dao.JobDao;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.entity.Job;
import cn.edu.zju.isst.service.JobService;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;
    @Autowired
    private CategoryDao categoryDao;
    
    @Override
    public List<Job> findAll(String categoryAlias, int pageSize, int page) {
        Category category = categoryDao.find(categoryAlias);
        if (null != category) {
            return jobDao.findAll(category.getId(), Job.STATUS_PUBLISHED, pageSize, page);
        } else {
            return new ArrayList<Job>();
        }
    }

    @Override
    public Job find(int id) {
        return jobDao.find(id);
    }
}