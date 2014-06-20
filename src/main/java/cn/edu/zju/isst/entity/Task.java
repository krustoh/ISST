package cn.edu.zju.isst.entity;

import java.util.Date;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("tasks")
public class Task {
    public static final int STATUS_HIDDEN = 0;
    public static final int STATUS_PUBLISHED = 1;
    
    public static final int TYPE_SURVEY = 0;
    
    @ID
    @Column
    private int id;
    
    @Column
    private int type;
    @Column
    private String name;
    
    @Column
    private String description;
    
    @Column("updated_at")
    private Date updatedAt;
    
    @Column("start_time")
    private Date startTime;
    
    @Column("expire_time")
    private Date expireTime;
    
    @Column
    private int status;
    
    private int finishId;
    
    public int getFinishId() {
        return finishId;
    }

    public void setFinishId(int finishId) {
        this.finishId = finishId;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getType() {
        return type;
    }
    
    public void setType(int type) {
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getExpireTime() {
        return expireTime;
    }
    
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String toString() {
        return name;
    }
}