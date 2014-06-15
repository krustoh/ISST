package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;

public interface ActivityService {
    public PaginationList<Activity> findAll(ActivitySearchCondition condition, int pageSize, int page);
    public PaginationList<Activity> findUserList(int userId, ActivitySearchCondition condition, int pageSize, int page);
    public List<Activity> findAll(Set<Integer> idset);
    public Activity find(int id);
    public Activity findForUser(int userId, int id);
    public int delete(Activity activity);
    public int delete(Set<Integer> idset);
    public void save(Activity activity);
    public int publish(Set<Integer> idset);
    public int publish(int id);
    public int hide(Set<Integer> idset);
    public Result participate(int activityId, int userId);
    public Result unparticipate(int activityId, int userId);
}