package cn.edu.zju.isst.service;

import java.util.List;

import cn.edu.zju.isst.entity.Activity;

public interface ActivityService {
    public List<Activity> findAllOfCampus(String keywords, int pageSize, int page);
    public Activity find(int id);
}