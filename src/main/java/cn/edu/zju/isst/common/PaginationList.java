package cn.edu.zju.isst.common;

import java.util.ArrayList;
import java.util.List;

import cn.edu.zju.isst.dao.DaoPagingable;

public class PaginationList<T> {
    private int page;
    private int pageSize;
    private int total = -1;
    private List<T> items;
    
    private SelectSQLBuilder sql;
    private DaoPagingable<T> dao;
    
    public PaginationList(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = 0;
    }
    
    public PaginationList(int page, int pageSize, DaoPagingable<T> dao, SelectSQLBuilder sql) {
        this.page = page;
        this.pageSize = pageSize;
        this.dao = dao;
        this.sql = sql;
    }
    
    public PaginationList(int page, int pageSize, int total, List<T> items) {
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.items = items;
    }
    
    public PaginationList(int page, int pageSize, List<T> items,  DaoPagingable<T> dao, SelectSQLBuilder sql) {
        this.page = page;
        this.pageSize = pageSize;
        this.dao = dao;
        this.sql = sql;
        this.items = items;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotal() {
        if (total < 0) {
            if (null != sql && null != dao) {
                total = dao.count(sql);
            } else {
                total = 0;
            }
        }
        return total;
    }

    public List<T> getItems() {
        if (null == items) {
            if (null != sql && null != dao) {
                items = dao.queryAll(sql);
            } else {
                items = new ArrayList<T>();
            }
        }
        return items;
    }
}