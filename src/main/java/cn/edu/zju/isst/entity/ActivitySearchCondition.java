package cn.edu.zju.isst.entity;

public class ActivitySearchCondition {
    private int cityId;
    private int userId;
    private int status = -1;
    private String keywords;
    private String poster;
    
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
    
    public String getKeywords() {
        return keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
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