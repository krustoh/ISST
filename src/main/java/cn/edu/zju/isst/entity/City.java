package cn.edu.zju.isst.entity;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("cities")
public class City {
    public static final int STATUS_HIDDEN = 0;
    public static final int STATUS_PUBLISHED = 1;
    
    @ID
    @Column
    private int id;
    
    @Column
    private String name;
    
    @Column("user_id")
    private int userId;
    
    @Column
    private int status;
    
    private UserSummary user;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserSummary getUser() {
        return user;
    }

    public void setUser(UserSummary user) {
        this.user = user;
    }
}