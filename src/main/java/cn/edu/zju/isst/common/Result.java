package cn.edu.zju.isst.common;

public class Result {
    private int status;
    private String message;
    
    public Result() {
        status = 0;
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
}