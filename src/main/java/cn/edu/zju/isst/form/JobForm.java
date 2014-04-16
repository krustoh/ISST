package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.Job;

public class JobForm {
    private int id;
    
    private int categoryId;
    
    private int userId;
    
    private int cityId;
    
    @NotBlank(message = "标题不能为空")
    private String title;
    
    @NotBlank(message = "公司不能为空")
    private String company;
    
    @NotBlank(message = "职位不能为空")
    private String position;
    
    private String content;
    
    private int status;
    
    public JobForm() {
        
    }
    
    public JobForm(Job job) {
        id = job.getId();
        categoryId = job.getCategoryId();
        userId = job.getUserId();
        cityId = job.getCityId();
        title = job.getTitle();
        company = job.getCompany();
        position = job.getPosition();
        content = job.getContent();
        status = job.getStatus();
    }
    
    public Job build() {
        Job job = new Job();
        bind(job);
        
        return job;
    }
    
    public void bind(Job job) {
        job.setTitle(title);
        job.setCategoryId(categoryId);
        job.setUserId(userId);
        job.setCityId(cityId);
        job.setCompany(company);
        job.setPosition(position);
        job.setContent(content);
        job.setStatus(status);
    }
    
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
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}