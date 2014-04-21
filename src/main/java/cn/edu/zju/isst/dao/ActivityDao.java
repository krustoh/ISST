package cn.edu.zju.isst.dao;

import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;

public interface ActivityDao extends Dao<Activity> {
    public int changeStatus(Set<Integer> idset, int status);
    public PaginationList<Activity> findAll(ActivitySearchCondition condition, int pageSize, int page);
}