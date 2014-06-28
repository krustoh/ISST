package cn.edu.zju.isst.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class FlashMessage {
    private String status;
    private String message;
    
    public FlashMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
    
    public static FlashMessage success(String message) {
        return new FlashMessage("success", message);
    }
    
    public static FlashMessage error(String message) {
        return new FlashMessage("error", message);
    }
    
    public static FlashMessage error(Result result) {
        return error(result.getMessage());
    }
    
    public static FlashMessage error(BindingResult result) {
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : result.getAllErrors()) {
            String s = error.getDefaultMessage();
            if (null != s && s.length() > 0) {
                sb.append(s).append("\n");
            }
        }
        return error(sb.toString());
    }
    
    public static FlashMessage notice(String message) {
        return new FlashMessage("notice", message);
    }
}