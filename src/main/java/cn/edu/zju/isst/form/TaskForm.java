package cn.edu.zju.isst.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import cn.edu.zju.isst.entity.Task;

public class TaskForm {
    private int id;
    
    private int type;

    @NotBlank(message = "任务名称不能为空")
    private String name;

    @NotBlank(message = "任务描述不能为空")
    private String description;

    @NotNull(message = "任务开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @NotNull(message = "任务截止时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireTime;
    
    private int status;

    public TaskForm() {
        
    }
    
    public TaskForm(Task task) {
        id = task.getId();
        name = task.getName();
        description = task.getDescription();
        type = task.getType();
        startTime = task.getStartTime();
        expireTime = task.getExpireTime();
        status = task.getStatus();
    }
    
    public Task build() {
        return bind(new Task());
    }
    
    public Task bind(Task task) {
        task.setId(id);
        task.setDescription(description);
        task.setExpireTime(expireTime);
        task.setName(name);
        task.setStartTime(startTime);
        task.setStatus(status);
        task.setType(type);
        
        return task;
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
}