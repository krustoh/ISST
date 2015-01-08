package cn.edu.zju.isst.dao;


import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.Message;
import cn.edu.zju.isst.entity.MessagePushBind;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MessagePushBindDaoImpl extends AbstractDao<MessagePushBind> implements MessagePushBindDao {
    @Override
    public List<MessagePushBind> findByStudentId(String studentId) {
        return queryAll(String.format("SELECT * FROM %s WHERE student_id=? ORDER BY id ASC", table), studentId);
    }

    @Override
    public void create(MessagePushBind entity) {
        String sql = "INSERT INTO messages_push_bind (user_id, student_id, channel_id)"+
                " VALUES (:userId, :studentId, :channelId)";
        Map<String,Object> para = new HashMap<String, Object>();
        para.put("userId",entity.getUserId());
        para.put("studentId",entity.getStudentId());
        para.put("channelId",entity.getChannelId());

        jdbcTemplate.update(sql,para);
    }


    @Override
    public boolean isBinded(MessagePushBind entity) {

        return jdbcTemplate.getJdbcOperations().queryForObject("SELECT COUNT(id) FROM messages_push_bind WHERE user_id=?", new Object[] {entity.getUserId()}, Integer.class) > 0 ? true : false;

    }
}