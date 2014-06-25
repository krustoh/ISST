package cn.edu.zju.isst.service;

import cn.edu.zju.isst.entity.AppRelease;

public interface AppReleaseService {
    AppRelease getLatestVersion();
}