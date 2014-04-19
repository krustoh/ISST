package cn.edu.zju.isst.entity;

public class JobSearchCondition {
    private int status = -1;
    private String keywords;
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getKeywords() {
        return keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}