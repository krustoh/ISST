package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.UserSummary;

public class ArchiveForm {
    private int id;
    
    @NotBlank(message = "标题不能为空")
    private String title;
    
    private int categoryId;
    
    private int userId;
    
    private String content;
    
    private int status = Archive.STATUS_PUBLISHED;
    
    private UserSummary user;
    
    public ArchiveForm() {
    }
    
    public ArchiveForm(Archive archive) {
        id = archive.getId();
        title = archive.getTitle();
        categoryId = archive.getCategoryId();
        content = archive.getContent();
        status = archive.getStatus();
        userId = archive.getUserId();
        user = archive.getUser();
    }
    
    public Archive build() {
        Archive archive = new Archive();
        bind(archive);
        
        return archive;
    }
    
    public void bind(Archive archive) {
        archive.setCategoryId(categoryId);
        archive.setTitle(title);
        archive.setContent(content);
        archive.setStatus(status);
        archive.setUserId(userId);
    }
    
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
    
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserSummary getUser() {
        return user;
    }
}