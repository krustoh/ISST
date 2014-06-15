package cn.edu.zju.isst.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class ApiResponse {
    private int status;
    private String message;
    private Object body;
    
    public ApiResponse(String message) {
        status = 0;
        this.message = message; 
    }
    
    public ApiResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public ApiResponse(Object body) {
        status = 0;
        this.body = body;
    }
    
    public ApiResponse(Result result) {
        status = result.getStatus();
        message = result.getMessage();
    }
    
    public ApiResponse(BindingResult result) {
        if (result.hasErrors()) {
            ObjectError error = result.getAllErrors().get(0);
            try {
                status = Integer.parseInt(error.getCode());
            } catch (NumberFormatException e) {
                status = 2;
            }
            message = error.getDefaultMessage();
        }
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Object getBody() {
        return body;
    }
    
    public void setBody(Object body) {
        this.body = body;
    }
}
