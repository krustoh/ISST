package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotEmpty;

public class AdministratorLoginForm {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    
    private boolean rememberMe;
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isRememberMe() {
        return rememberMe;
    }
    
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
