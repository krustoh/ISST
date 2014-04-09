package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.User;

public class AlumniForm {
    private int id;
    
    @NotBlank(message = "学号不能为空")
    private String username;
    private String password;
    private int grade;
    private int classId;
    private int cityId;
    private int majorId;
    private int gender;
    @NotBlank(message = "姓名不能为空")
    private String name;
    private String email;
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
    
    public AlumniForm(User user) {
        id = user.getId();
        username = user.getUsername();
        grade = user.getGrade();
        classId = user.getClassId();
        cityId = user.getCityId();
        majorId = user.getMajorId();
        gender = user.getGender();
        name = user.getName();
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
    
    public User build() {
        User user = new User();
        bind(user);
        return user;
    }
    
    public void bind(User user) {
        user.setUsername(username);
        user.setGrade(grade);
        user.setClassId(classId);
        user.setCityId(cityId);
        user.setMajorId(majorId);
        user.setGender(gender);
        user.setName(name);
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
    
    public int getCityId() {
        return cityId;
    }
    
    public void setCityId(int cityId) {
        this.cityId = cityId;
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