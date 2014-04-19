package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.CollectedNews;

public class CollectedNewsForm {
    private int id;
    
    @NotBlank(message = "标题不能为空")
    private String title;
    
    private String content;
    
    public CollectedNewsForm() {
    }
    
    public CollectedNewsForm(CollectedNews news) {
        id = news.getId();
        title = news.getTitle();
        content = news.getContent();
    }
    
    public CollectedNews build() {
        CollectedNews news = new CollectedNews();
        bind(news);
        
        return news;
    }
    
    public void bind(CollectedNews news) {
        news.setTitle(title);
        news.setContent(content);
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
    
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}