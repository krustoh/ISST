package cn.edu.zju.isst.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.dao.CategoryDao;
import cn.edu.zju.isst.entity.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    
    @Override
    public Category find(int id) {
        return categoryDao.find(id);
    }

    @Override
    public Category find(String alias) {
        return categoryDao.find(alias);
    }
}