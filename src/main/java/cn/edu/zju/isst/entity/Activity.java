package cn.edu.zju.isst.entity;

import java.util.Date;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("activities")
public class Activity {
    public static final int STATUS_HIDDEN = 0;
    public static final int STATUS_PUBLISHED = 1;
    
    @ID
    @Column
    private int id;
    
    @Column("city_id")
    private int cityId;
    
    @Column("user_id")
    private int userId;

    @Column
    private String title;
    
    @Column
    private String location;
    
    @Column("picture_path")
    private String picturePath;

    @Column
    private String description;

    @Column
    private String content;

    @Column("start_time")
    private Date startTime;

    @Column("expire_time")
    private Date expireTime;

    @Column("updated_at")
    private Date updatedAt;

    @Column
    private int status;
    
    private String cityName;
    
    private UserSummary user;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getCityId() {
        return cityId;
    }
    
    public void setCityId(int cityId) {
        this.cityId = cityId;
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
    
    public String getPicturePath() {
        return picturePath;
    }
    
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    
    public String getPicture() {
        return WebUtils.parseUploadedUrl(picturePath);
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setDescriptionFromContent(String content) {
        if (null == content) {
            this.description = "" ;
        } else {
            String filtered = content.replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "").replaceAll("\\s", "");
            this.description = filtered.substring(0, filtered.length() < 50 ? filtered.length() : 50);
        }
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
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
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public UserSummary getUser() {
        return user;
    }

    public void setUser(UserSummary user) {
        this.user = user;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String toString() {
        return title;
    }
}