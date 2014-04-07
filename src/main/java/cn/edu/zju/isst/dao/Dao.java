package cn.edu.zju.isst.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.edu.zju.isst.common.SelectSQLBuilder;

public interface Dao<T> {
    public void insert(final T entity);
    public int update(T entity);
    public void save(final T entity);
    public int delete(T entity);
    public int delete(Set<Integer> idset);
    public int count(SelectSQLBuilder select);
    public T find(int id);
    public List<T> findAll(Set<Integer> idset);
    public T query(String sql);
    public T query(String sql, Object...params);
    public T query(String sql, Map<String, Object> params);
    public T query(SelectSQLBuilder select);
    public List<T> queryAll(String sql);
    public List<T> queryAll(SelectSQLBuilder select);
    public List<T> queryAll(String sql, Object...params);
    public List<T> queryAll(String sql, Map<String, Object> params);
    public boolean exists(int id);
    public SelectSQLBuilder select();
    public SelectSQLBuilder select(String field);
}