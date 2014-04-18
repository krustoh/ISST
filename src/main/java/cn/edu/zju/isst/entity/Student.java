package cn.edu.zju.isst.entity;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("students")
public class Student {
    @ID
    @Column
    private int id;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    private int grade;
    
    @Column("class_id")
    private int classId;
    
    private String className;
    
    @Column
    private String major;
    
    private int gender;
    
    @Column
    private String sexual;
    
    @Column
    private String name;
    
    @Column
    private String email;
    
    @Column("tel")
    private String phone;
    
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
    
    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        if (2 == gender) {
            this.sexual = "女";
        } else {
            this.sexual = "男";
        }
        
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        if ("女".equals(sexual)) {
            this.gender = 2;
        } else {
            this.gender = 1;
        }
        this.sexual = sexual;
    }
}