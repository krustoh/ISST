package cn.edu.zju.isst.entity;

import java.util.Date;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("task_survey")
public class TaskSurvey {
    @ID
    @Column
    private int id;
    
    @Column("task_id")
    private int taskId;
    
    @Column("user_id")
    private int userId;
    
    @Column("option_id")
    private int optionId;

    @Column("option_other")
    private String optionOther;

    @Column
    private String remarks;
    
    @Column("created_at")
    private Date createdAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getOptionOther() {
        return optionOther;
    }

    public void setOptionOther(String optionOther) {
        this.optionOther = optionOther;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}