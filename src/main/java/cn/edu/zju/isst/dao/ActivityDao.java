package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.entity.Activity;

public interface ActivityDao extends Dao<Activity> {
    public List<Activity> findAllOfCampus(String keywords, int pageSize, int page);
}