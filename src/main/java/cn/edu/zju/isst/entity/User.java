package cn.edu.zju.isst.entity;

import org.apache.commons.codec.digest.Md5Crypt;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("users")
public class User implements PrivateProfile {
    @ID
    @Column
    private int id;
    
    @Column
    private String username;
    
    @Column
    private String password;
    
    @Column
    private int grade;
    
    @Column("class_id")
    private int classId;
    
    @Column("city_id")
    private int cityId;
    
    @Column("major_id")
    private int majorId;
    
    @Column
    private int gender;
    
    @Column
    private String name;
    
    @Column
    private String email;
    
    @Column
    private String phone;
    
    @Column
    private String qq;
    
    @Column
    private String signature;
    
    @Column
    private String company;
    
    @Column
    private String position;
    
    @Column("city_principal")
    private boolean cityPrincipal;
    
    @Column("private_phone")
    private boolean privatePhone;
    
    @Column("private_email")
    private boolean privateEmail;
    
    @Column("private_qq")
    private boolean privateQQ;
    
    @Column("private_company")
    private boolean privateCompany;
    
    @Column("private_position")
    private boolean privatePosition;
    
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
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
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
    
    public void setCityPrincipal(boolean cityPrincipal) {
        this.cityPrincipal = cityPrincipal;
    }
    
    public boolean isCityPrincipal() {
        return cityPrincipal;
    }

    public boolean isPrivatePhone() {
        return privatePhone;
    }

    public void setPrivatePhone(boolean privatePhone) {
        this.privatePhone = privatePhone;
    }

    public boolean isPrivateEmail() {
        return privateEmail;
    }

    public void setPrivateEmail(boolean privateEmail) {
        this.privateEmail = privateEmail;
    }

    public boolean isPrivateQQ() {
        return privateQQ;
    }

    public void setPrivateQQ(boolean privateQQ) {
        this.privateQQ = privateQQ;
    }

    public boolean isPrivateCompany() {
        return privateCompany;
    }

    public void setPrivateCompany(boolean privateCompany) {
        this.privateCompany = privateCompany;
    }

    public boolean isPrivatePosition() {
        return privatePosition;
    }

    public void setPrivatePosition(boolean privatePosition) {
        this.privatePosition = privatePosition;
    }
}
