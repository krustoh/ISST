package cn.edu.zju.isst.service;

import cn.edu.zju.isst.dao.AppReleaseDaoImpl;
import cn.edu.zju.isst.dao.MessagePushBindDao;
import cn.edu.zju.isst.entity.AppRelease;
import cn.edu.zju.isst.entity.MessagePushBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagePushBindServiceImpl implements MessagePushBindService {
    @Autowired
    private MessagePushBindDao messagePushBindDao;

    @Override
    public void create(MessagePushBind bind) {
        messagePushBindDao.create(bind);
    }

    @Override
    public boolean isBinded(MessagePushBind bind) {
        return messagePushBindDao.isBinded(bind);
    }

    @Override
    public List<MessagePushBind> getBindinfo(String studentId) {
        return messagePushBindDao.findByStudentId(studentId);
    }
}
