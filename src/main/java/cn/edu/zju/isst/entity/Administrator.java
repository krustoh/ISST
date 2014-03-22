package cn.edu.zju.isst.entity;

import org.apache.commons.codec.digest.Md5Crypt;

public class Administrator {
    public static final int SUPER = 0xffffffff;
    public static final int ADMIN_ALUMNI = 0x00000001;
    public static final int ADMIN = 0x00000003;
    
    private int id;
    private String username;
    private String password;
    private int roles;
    
    public boolean hasRole(int role) {
        return (roles & role) == role;
    }
    
    public void addRole(int role) {
        roles |= role;
    }
    
    public void removeRole(int role) {
        roles &= (~role);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }
    
    public void encryptPassword(String password) {
        this.password = Md5Crypt.md5Crypt(password.getBytes());
    }
    
    public boolean validatePassword(String password) {
        if (null == this.password) {
            return false;
        }
        return this.password.equals(Md5Crypt.md5Crypt(password.getBytes(), this.password));
    }
}
