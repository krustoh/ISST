package cn.edu.zju.isst.dao;

import java.util.List;

public interface Dao<T> {
    public void insert(final T entity);
    public int update(T entity);
    public int delete(T entity);
    public T find(int id);
    public T findBySql(String sql);
    public T findBySql(String sql, Object...params);
    public List<T> findAllBySql(String sql);
    public List<T> findAllBySql(String sql, Object...params);
    public boolean exists(int id);
}