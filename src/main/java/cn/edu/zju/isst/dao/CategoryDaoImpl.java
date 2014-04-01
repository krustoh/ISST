package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.entity.Category;

@Repository
public class CategoryDaoImpl extends AbstractDao<Category> implements CategoryDao {

    @Override
    public Category find(String alias) {
        return query("SELECT * FROM categories WHERE alias=?", alias);
    }
}