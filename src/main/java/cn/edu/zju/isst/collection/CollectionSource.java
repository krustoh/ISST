package cn.edu.zju.isst.collection;

import java.util.List;

public interface CollectionSource <T extends CollectionEntity>{
    public List<T> collect();
    public List<T> collectList();
    public void parseDetail(T entity);
}