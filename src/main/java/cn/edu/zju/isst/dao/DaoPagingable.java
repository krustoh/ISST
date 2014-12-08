package cn.edu.zju.isst.dao;

import java.util.List;

import cn.edu.zju.isst.common.SelectSQLBuilder;

public interface DaoPagingable<T> {
    public List<T> queryAll(SelectSQLBuilder select);
    public int count(SelectSQLBuilder select);
}