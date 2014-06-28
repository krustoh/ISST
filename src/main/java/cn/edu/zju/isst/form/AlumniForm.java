package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.StudentUser;

public class AlumniForm {
    private int id;
    private int cityId;
    @NotBlank(message = "Email不能为空")
    private String email;
    @NotBlank(message = "手机号不能为空")
    private String phone;
    private String qq;
    private String signature;
    private String company;
    private String position;
    
    private boolean privatePhone;
    private boolean privateEmail;
    private boolean privateQQ;
    private boolean privateCompany;
    private boolean privatePosition;
    
    public AlumniForm() {
    }
    
    public AlumniForm(StudentUser user) {
        id = user.getId();
        cityId = user.getCityId();
        email = user.getEmail();
        phone = user.getPhone();
        qq = user.getQq();
        signature = user.getSignature();
        company = user.getSignature();
        position = user.getPosition();
        privatePhone = user.isPrivatePhone();
        privateEmail = user.isPrivateEmail();
        privateQQ = user.isPrivateQQ();
        privateCompany = user.isPrivateCompany();
        privatePosition = user.isPrivatePosition();
    }
    
    public StudentUser build() {
        StudentUser user = new StudentUser();
        return bind(user);
    }
    
    public StudentUser bind(StudentUser user) {
        user.setCityId(cityId);
        user.setEmail(email);
        user.setPhone(phone);
        user.setQq(qq);
        user.setSignature(signature);
        user.setCompany(company);
        user.setPosition(position);
        user.setPrivateCompany(privateCompany);
        user.setPrivateEmail(privateEmail);
        user.setPrivatePhone(privatePhone);
        user.setPrivatePosition(privatePosition);
        user.setPrivateQQ(privateQQ);
        
        return user;
    }
    
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