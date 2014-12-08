package cn.edu.zju.isst.dao;

import org.springframework.stereotype.Repository;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.SelectSQLBuilder;
import cn.edu.zju.isst.entity.Message;

@Repository
public class MessageDaoImpl extends AbstractDao<Message> implements MessageDao {

    @Override
    public PaginationList<Message> findAll(int status, int pageSize, int page) {
        SelectSQLBuilder select = select("*")
                .orderBy("created_at DESC, id DESC");
        
        if (status >= 0) {
            select.where("status=:status").addParam("status", status);
        }
        
        if (pageSize > 0) {
            select.paging(page, pageSize);
        }
        
        return new PaginationList<Message>(page, pageSize, this, select);
    }
}