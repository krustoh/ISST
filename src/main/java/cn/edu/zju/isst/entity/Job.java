package cn.edu.zju.isst.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.zju.isst.admin.controller.AppConfig;

public class Job {
    public static final int STATUS_HIDDEN = 0;
    public static final int STATUS_PUBLISHED = 1;
    
    private int id;
    private int category_id;
    private int city_id;
    private int user_id;
    private String title;
    private String company;
    private String position;
    private Date updated_at;
    private int status;
    private UserSummary user;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCategoryId() {
        return category_id;
    }
    
    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }
    
    public int getCityId() {
        return city_id;
    }
    
    public void setCityId(int city_id) {
        this.city_id = city_id;
    }
    
    public int getUserId() {
        return user_id;
    }
    
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public Date getUpdatedAt() {
        return updated_at;
    }
    
    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    public void setUpdatedAt(String updated_at) {
        try {
            this.updated_at = new SimpleDateFormat(AppConfig.dateTimeFormatter).parse(updated_at);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
