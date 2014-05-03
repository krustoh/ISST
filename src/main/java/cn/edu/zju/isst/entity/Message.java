package cn.edu.zju.isst.entity;

import java.util.Date;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("messages")
public class Message {
    public static final int STATUS_UNPUSHED = 0;
    public static final int STATUS_PUSHED = 1;
    
    @ID
    @Column
    private int id;
    @Column
    private String title;
    @Column
    private String content;
    @Column("created_at")
    private Date createdAt;
    @Column
    private int status;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String toString() {
        return title;
    }
}