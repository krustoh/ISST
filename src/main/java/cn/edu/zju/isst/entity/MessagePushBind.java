package cn.edu.zju.isst.entity;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

import java.util.Date;

/**
 * Created by zhoubo on 15/1/7.
 */


@Entity("messages_push_bind")
public class MessagePushBind {

    @ID
    @Column
    private int id;

    @Column("student_id")
    private String StudentId;

    @Column("user_id")
    private String userId;

    @Column("channel_id")
    private long channelId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "MessagePushBind{" +
                "id=" + id +
                ", StudentId='" + StudentId + '\'' +
                ", userId='" + userId + '\'' +
                ", channelId=" + channelId +
                '}';
    }
}
