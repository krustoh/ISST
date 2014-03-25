package cn.edu.zju.isst.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.UserDao;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void synchronizeUsers() {
        userDao.synchronizeUsers();
    }
}