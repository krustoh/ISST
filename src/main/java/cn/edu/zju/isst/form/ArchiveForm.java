package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotEmpty;

import cn.edu.zju.isst.entity.Archive;

public class ArchiveForm {
    private int id;
    
    @NotEmpty(message = "标题不能为空")
    private String title;
    
    @NotEmpty(message = "分类不能为空")
    private int categoryId;
    
    private int userId;
    
    private String content;
    
    private int status = Archive.STATUS_PUBLISHED;
    
    public ArchiveForm() {
    }
    
    public ArchiveForm(Archive archive) {
        id = archive.getId();
        title = archive.getTitle();
        categoryId = archive.getCategoryId();
        content = archive.getContent();
        status = archive.getStatus();
        userId = archive.getUserId();
    }
    
    public Archive buildArchive() {
        Archive archive = new Archive();
        archive.setId(id);
        archive.setCategoryId(categoryId);
        archive.setTitle(title);
        archive.setContent(content);
        archive.setStatus(archive.getStatus());
        archive.setUserId(userId);
        return archive;
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
}