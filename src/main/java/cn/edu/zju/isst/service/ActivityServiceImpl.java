package cn.edu.zju.isst.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.ActivityDao;
import cn.edu.zju.isst.entity.Activity;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    
    @Override
    public List<Activity> findAllOfCampus(String keywords, int pageSize, int page) {
        return activityDao.findAllOfCampus(keywords, pageSize, page);
    }

    @Override
    public Activity find(int id) {
        return activityDao.find(id);
    }
}