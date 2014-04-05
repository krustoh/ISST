package cn.edu.zju.isst.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.zju.isst.common.AppConfig;
import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("archives")
public class Archive {
    public static final int STATUS_HIDDEN = 0;
    public static final int STATUS_PUBLISHED = 1;
    
    @ID
    @Column
    private int id;

    @Column("category_id")
    private int categoryId;
    
    @Column("user_id")
    private int userId;

    @Column
    private String title;

    @Column
    private String picture;

    @Column
    private String description;

    @Column
    private String content;

    @Column("updated_at")
    private Date updatedAt;

    @Column
    private int status;
    
    private UserSummary user;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
    
    public void setDescriptionFromContent(String content) {
        if (null == content) {
            return ;
        }
        
        String filtered = content.replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "").replaceAll("\\s", "");
        this.description = filtered.substring(0, filtered.length() < 50 ? filtered.length() : 50);
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public void setUpdatedAt(String updatedAt) {
        try {
            this.updatedAt = new SimpleDateFormat(AppConfig.dateTimeFormatter).parse(updatedAt);
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