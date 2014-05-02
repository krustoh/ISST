package cn.edu.zju.isst.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.ActivityDao;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;
    
    @Override
    public PaginationList<Activity> findAll(ActivitySearchCondition condition, int pageSize, int page) {
        return activityDao.findAll(condition, pageSize, page);
    }

    @Override
    public Activity find(int id) {
        return activityDao.find(id);
    }

    @Override
    public List<Activity> findAll(Set<Integer> idset) {
        return activityDao.findAll(idset);
    }

    @Override
    public int delete(Activity activity) {
        return activityDao.delete(activity);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return activityDao.delete(idset);
    }

    @Override
    public void save(Activity activity) {
        activity.setUpdatedAt(new Date());
        activityDao.save(activity);
    }

    @Override
    public int publish(Set<Integer> idset) {
        return activityDao.changeStatus(idset, Activity.STATUS_PUBLISHED); 
    }

    @Override
    public int hide(Set<Integer> idset) {
        return activityDao.changeStatus(idset, Activity.STATUS_HIDDEN);
    }
}