package cn.edu.zju.isst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.AppReleaseDaoImpl;
import cn.edu.zju.isst.entity.AppRelease;

@Service
public class AppReleaseServiceImpl implements AppReleaseService {
    @Autowired
    private AppReleaseDaoImpl appReleaseDao;
    
    @Override
    public AppRelease getLatestVersion() {
        return appReleaseDao.getLatestVersion();
    }
}
