package cn.edu.zju.isst.service;

import java.util.List;
import java.util.Set;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.entity.Message;

public interface MessageService {
    public int push(Set<Integer> idset);
    public int push(Message message);
    public List<Message> findAll(Set<Integer> idset);
    public PaginationList<Message> findAll(int status, int pageSize, int page);
    public Message find(int id);
    public int delete(Message message);
    public int delete(Set<Integer> idset);
    public void save(Message message);
}