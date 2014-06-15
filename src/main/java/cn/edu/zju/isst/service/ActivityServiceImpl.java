package cn.edu.zju.isst.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.Result;
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
    public PaginationList<Activity> findUserList(int userId, ActivitySearchCondition condition, int pageSize, int page) {
        PaginationList<Activity> list = activityDao.findAll(condition, pageSize, page);
        for (Activity activity : list.getItems()) {
            activity.setParticipated(activityDao.hasParticipated(activity.getId(), userId));
        }
        
        return list;
    }

    @Override
    public Activity find(int id) {
        return activityDao.find(id);
    }

    @Override
    public Activity findForUser(int userId, int id) {
        Activity activity = activityDao.find(id);
        if (null != activity) {
            activity.setParticipated(activityDao.hasParticipated(activity.getId(), userId));
        }
        
        return activity;
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

    @Override
    public Result participate(int activityId, int userId) {
        Date createdAt = new Date();
        Activity activity = activityDao.find(activityId);
        if (null == activity) {
            return new Result(30, "活动不存在");
        }
        
        if (activity.getStartTime().getTime() > createdAt.getTime()) {
            return new Result(31, "活动尚未开始，不能报名");
        } else if (activity.getExpireTime().getTime() < createdAt.getTime()) {
            return new Result(32, "活动已结束");
        }
        
        if (!activityDao.participate(activityId, userId)) {
            return new Result(33, "你已报名，请勿重复报名");
        }
        
        return new Result();
    }

    @Override
    public Result unparticipate(int activityId, int userId) {
        if (activityDao.unparticipate(activityId, userId)) {
            return new Result();
        } else {
            return new Result("你尚未报名");
        }
    }

    @Override
    public int publish(int id) {
        return activityDao.changeStatus(id, Activity.STATUS_PUBLISHED);
    }
}