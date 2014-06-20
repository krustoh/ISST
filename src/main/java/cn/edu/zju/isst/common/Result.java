package cn.edu.zju.isst.common;

public class Result {
    private int status;
    private String message;
    private Object body;
    
    public Result() {
        status = 0;
    }
    public Result(Object body) {
        this.body = body;
    }
    
    public Result(String message) {
        status = 1;
        this.message = message;
    }
    
    public Result(int status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public int getStatus() {
        return status;
    }
    
    public boolean valid() {
        return 0 == status;
    }
    
    public void setBody(Object body) {
        this.body = body;
    }

    public Object getBody() {
        return body;
    }
}