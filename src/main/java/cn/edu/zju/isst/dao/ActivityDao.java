package cn.edu.zju.isst.dao;

import java.util.Date;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;
import cn.edu.zju.isst.entity.StudentUser;

public interface ActivityDao extends Dao<Activity> {
    public int changeStatus(Set<Integer> idset, int status);
    public int changeStatus(int id, int status);
    public PaginationList<Activity> findAll(ActivitySearchCondition condition, int pageSize, int page);
    public PaginationList<Activity> findUserParticipatedList(int userId, int pageSize, int page);
    public boolean participate(int activityId, int userId);
    public boolean participate(int activityId, int userId, Date createdAt);
    public boolean unparticipate(int activityId, int userId);
    public boolean hasParticipated(int activityId, int userId);
}