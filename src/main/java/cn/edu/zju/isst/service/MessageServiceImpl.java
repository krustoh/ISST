package cn.edu.zju.isst.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.dao.MessagePushBindDao;
import cn.edu.zju.isst.entity.MessagePushBind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.zju.isst.common.BccsApi;
import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.dao.MessageDao;
import cn.edu.zju.isst.entity.Message;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private MessagePushBindDao messagePushBindDao;

    @Override
    public int push(Set<Integer> idset) {
        int success = 0;

        for (Message message : findAll(idset)) {
            success += push(message);
        }

        return success;
    }

    @Override
    public int push(Message message) {
        int success = BccsApi.pushAndroidBroadcastMessage("message." + message.getId(), message.getTitle(), message.getContent());
        if (success > 0) {
            message.setStatus(Message.STATUS_PUSHED);
            messageDao.save(message);
        }

        return success;
    }

    @Override
    public int push(Message message, String studentId) {
        List<MessagePushBind> binds =  messagePushBindDao.findByStudentId(studentId);
        int success = 0;
        for (MessagePushBind bind: binds)
        {
            success += BccsApi.pushAndroidUnitcastMessage("message." + message.getId(), message.getTitle(), message.getContent(),bind.getUserId());

        }
        if (success > 0) {
            message.setStatus(Message.STATUS_PUSHED);
            messageDao.save(message);
        }
        return success;
    }

    @Override
    public int push(Message message, Set<String> studentIdSet) {
        return 0;
    }

    @Override
    public List<Message> findAll(Set<Integer> idset) {
        return messageDao.findAll(idset);
    }

    @Override
    public PaginationList<Message> findAll(int status, int pageSize, int page) {
        return messageDao.findAll(status, pageSize, page);
    }

    @Override
    public Message find(int id) {
        return messageDao.find(id);
    }

    @Override
    public int delete(Message message) {
        return messageDao.delete(message);
    }

    @Override
    public int delete(Set<Integer> idset) {
        return messageDao.delete(idset);
    }

    @Override
    public void save(Message message) {
        message.setCreatedAt(new Date());
        messageDao.save(message);
    }
}