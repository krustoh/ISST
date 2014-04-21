package cn.edu.zju.isst.entity;

import org.apache.commons.codec.digest.Md5Crypt;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("admins")
public class Administrator {
    public static final int SUPER = 0xffffffff;
    public static final int ADMIN_BASE = 0x00000001;
    public static final int ADMIN_ALUMNI = 0x00000003;
    public static final int ADMIN = 0x00000007;
    
    @ID
    @Column
    private int id;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @Column
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
    
    public static String encryptPassword(String password) {
        return Md5Crypt.md5Crypt(password.getBytes());
    }
    
    public static boolean validatePassword(String storedPassword, String password) {
        if (null == storedPassword || null == password) {
            return false;
        }
        return storedPassword.equals(Md5Crypt.md5Crypt(password.getBytes(), storedPassword));
    }
}