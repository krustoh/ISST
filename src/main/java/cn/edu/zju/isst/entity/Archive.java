package cn.edu.zju.isst.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Archive {
    public static final int STATUS_HIDDEN = 0;
    public static final int STATUS_PUBLISHED = 1;
    
    private int id;
    private int category_id;
    private int user_id;
    private String title;
    private String picture;
    private String description;
    private String content;
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
    
    public String getPicture() {
        return picture;
    }
    
    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getUpdatedAt() {
        return updated_at;
    }
    
    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    public void setUpdatedAt(String updated_at) {
        try {
            this.updated_at = new SimpleDateFormat().parse(updated_at);
        } catch (ParseException e) {
            this.updated_at = new Date(System.currentTimeMillis());
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
