package cn.edu.zju.isst.form;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Activity;

public class ActivityForm {
    private int id;
    private int cityId;
    private int userId;
    @NotBlank(message = "活动名称不能为空")
    private String title;
    @NotBlank(message = "活动地址不能为空")
    private String location;
    private MultipartFile pictureFile;
    private String picture;
    private String content;
    @NotBlank(message = "活动开始时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @NotBlank(message = "活动截止时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expireTime;
    private int status = Activity.STATUS_PUBLISHED;
    private String poster;
    
    public ActivityForm() {
        
    }
    
    public ActivityForm(Activity activity) {
        id = activity.getId();
        cityId = activity.getCityId();
        userId = activity.getUserId();
        title = activity.getTitle();
        location = activity.getLocation();
        content = activity.getContent();
        startTime = activity.getStartTime();
        expireTime = activity.getExpireTime();
        status = activity.getStatus();
        picture = activity.getPicture();
        if (null != activity.getUser()) {
            poster = activity.getUser().getUsername();
        }
    }
    
    public Activity build() {
        Activity activity = new Activity();
        bind(activity);
        
        return activity;
    }
    
    public void bind(Activity activity) {
        activity.setCityId(cityId);
        activity.setUserId(userId);
        activity.setTitle(title);
        activity.setLocation(location);
        activity.setContent(content);
        activity.setStartTime(startTime);
        activity.setExpireTime(expireTime);
        activity.setStatus(status);
        
        String picturePath = WebUtils.saveUploadedFile(pictureFile);
        if (null != picturePath) {
            String oldPicturePath = activity.getPicturePath();
            if (null != oldPicturePath) {
                WebUtils.deleteUploadedFile(oldPicturePath);
            }
            activity.setPicturePath(picturePath);
        }
    }
    
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
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getPicture() {
        return picture;
    }
    
    public MultipartFile getPictureFile() {
        return pictureFile;
    }
    
    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
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
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}