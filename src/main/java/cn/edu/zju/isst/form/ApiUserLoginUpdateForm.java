package cn.edu.zju.isst.form;

public class ApiUserLoginUpdateForm {
    private int userId;
    private String token;
    private long timestamp;
    private double logitude;
    private double tatitude;
    
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
    
    public double getLogitude() {
        return logitude;
    }
    
    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }
    
    public double getTatitude() {
        return tatitude;
    }
    
    public void setTatitude(double tatitude) {
        this.tatitude = tatitude;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}