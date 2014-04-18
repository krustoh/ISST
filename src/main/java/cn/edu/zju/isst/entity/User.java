package cn.edu.zju.isst.entity;

import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("users")
public class User {
    @ID
    @Column
    private int id;
    
    @Column("city_id")
    private int cityId;
    
    private String cityName;
    
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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
    
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}