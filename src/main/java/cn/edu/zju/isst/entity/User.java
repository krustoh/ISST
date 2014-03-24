package cn.edu.zju.isst.entity;

import org.apache.commons.codec.digest.Md5Crypt;

public class User {
    private int id;
    private String username;
    private String password;
    private int grade;
    private int class_id;
    private int city_id;
    private int major_id;
    private int gender;
    private String name;
    private String email;
    private String phone;
    private String qq;
    private String signature;
    private String company;
    private String position;
    private boolean city_principal;
    private boolean private_phone;
    private boolean private_email;
    private boolean private_qq;
    private boolean private_company;
    private boolean private_position;
    
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
    
    public void encryptPassword(String password) {
        this.password = Md5Crypt.md5Crypt(password.getBytes());
    }
    
    public boolean validatePassword(String password) {
        if (null == this.password) {
            return false;
        }
        return this.password.equals(Md5Crypt.md5Crypt(password.getBytes(), this.password));
    }

    public int getCityId() {
        return city_id;
    }

    public void setCityId(int city_id) {
        this.city_id = city_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getClassId() {
        return class_id;
    }

    public void setClassId(int class_id) {
        this.class_id = class_id;
    }

    public int getMajorId() {
        return major_id;
    }

    public void setMajorId(int major_id) {
        this.major_id = major_id;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public void setCityPrincipal(boolean city_principal) {
        this.city_principal = city_principal;
    }
    
    public boolean isCityPrincipal() {
        return city_principal;
    }

    public boolean isPrivatePhone() {
        return private_phone;
    }

    public void setPrivatePhone(boolean private_phone) {
        this.private_phone = private_phone;
    }

    public boolean isPrivateEmail() {
        return private_email;
    }

    public void setPrivateEmail(boolean private_email) {
        this.private_email = private_email;
    }

    public boolean isPrivateQQ() {
        return private_qq;
    }

    public void setPrivateQQ(boolean private_qq) {
        this.private_qq = private_qq;
    }

    public boolean isPrivateCompany() {
        return private_company;
    }

    public void setPrivateCompany(boolean private_company) {
        this.private_company = private_company;
    }

    public boolean isPrivatePosition() {
        return private_position;
    }

    public void setPrivatePosition(boolean private_position) {
        this.private_position = private_position;
    }
}
