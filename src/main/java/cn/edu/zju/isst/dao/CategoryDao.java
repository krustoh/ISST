package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.entity.Category;

public interface CategoryDao {
    public Category find(int id);
    public Category find(String alias);
}