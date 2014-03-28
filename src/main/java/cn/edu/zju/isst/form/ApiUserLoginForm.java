package cn.edu.zju.isst.form;

public class ApiUserLoginForm extends UserLoginForm {
    private String token;
    private long timestamp;
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}