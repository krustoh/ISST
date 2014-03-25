package cn.edu.zju.isst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.JobDao;
import cn.edu.zju.isst.entity.Job;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobDao jobDao;

    @Override
    public Job find(int id) {
        return jobDao.find(id);
    }
}