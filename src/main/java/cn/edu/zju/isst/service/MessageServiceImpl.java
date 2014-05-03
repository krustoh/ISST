package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

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
        messageDao.save(message);
    }
}