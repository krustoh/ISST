package cn.edu.zju.isst.service;

import cn.edu.zju.isst.entity.AppRelease;
import cn.edu.zju.isst.entity.MessagePushBind;

import java.util.List;

public interface MessagePushBindService {
   public List<MessagePushBind> getBindinfo(String studentId);
    public void create(MessagePushBind bind);
    public boolean isBinded(MessagePushBind bind);
}