package cn.edu.zju.isst.dao;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Message;
import cn.edu.zju.isst.entity.MessagePushBind;

import java.util.List;

public interface MessagePushBindDao extends Dao<MessagePushBind> {

    public List<MessagePushBind> findByStudentId(String studentId);
    public void create(MessagePushBind entity);
    public boolean isBinded(MessagePushBind entity);
}