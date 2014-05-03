package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Message;

public interface MessageDao extends Dao<Message> {
    public PaginationList<Message> findAll(int status, int pageSize, int page);
}