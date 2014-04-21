package cn.edu.zju.isst.entity;

public class CitySearchCondition {
    private String keywords;
    private int status = -1;
    
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
}