package cn.edu.zju.isst.entity;

public class City {
    private int id;
    private String name;
    private int user_id;
    private int status;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getUserId() {
        return user_id;
    }
    
    public void setUserId(int userId) {
        this.user_id = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
