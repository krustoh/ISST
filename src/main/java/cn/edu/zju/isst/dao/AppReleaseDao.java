package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.entity.AppRelease;

import java.util.List;

public interface AppReleaseDao extends Dao<AppRelease> {
    public List<AppRelease> findAll();
    public AppRelease getLatestVersion();
}