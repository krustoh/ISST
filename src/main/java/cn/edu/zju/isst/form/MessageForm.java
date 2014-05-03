package cn.edu.zju.isst.form;

import cn.edu.zju.isst.entity.Message;

public class MessageForm {
    private int id;
    private String title;
    private String content;
    
    public Message build() {
        Message message = new Message();
        
        message.setTitle(title);
        message.setContent(content);
        
        return message;
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