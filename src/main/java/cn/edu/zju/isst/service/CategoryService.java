package cn.edu.zju.isst.service;

import cn.edu.zju.isst.entity.Category;

public interface CategoryService {
    public Category find(int id);
    public Category find(String alias);
}