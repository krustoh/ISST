package cn.edu.zju.isst.collection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.zju.isst.admin.controller.AppConfig;

public class CollectionEntity {
    private int id;
    private String title;
    private String content;
    private Date postTime;
    private String url;
    
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
    
    public Date getPostTime() {
        return postTime;
    }
    
    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
    
    public void setPostTime(String post_time) {
        try {
            this.postTime = new SimpleDateFormat(AppConfig.dateTimeFormatter).parse(post_time);
        } catch (ParseException e) {
            this.postTime = new Date();
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String toString() {
        return "[" + id + ", " + title + ", " + postTime + ", " + url + ", " + content + "]";
    }
}