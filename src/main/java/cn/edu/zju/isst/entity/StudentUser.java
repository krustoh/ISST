package cn.edu.zju.isst.entity;

import org.apache.commons.codec.digest.Md5Crypt;

public class StudentUser implements PrivateProfile {
    private int id;
    
    private String username;
    
    private String password;
    
    private int grade;
    
    private int classId;
    
    private String className;
    
    private String major;
    
    private int gender;
    
    private String name;
    
    private String email;
    
    private String phone;
    
    private int cityId;
    
    private String cityName;
    
    private String qq;
    
    private String signature;
    
    private String company;
    
    private String position;
    
    private boolean cityPrincipal;
    
    private boolean privatePhone;
    
    private boolean privateEmail;
    
    private boolean privateQQ;
    
    private boolean privateCompany;
    
    private boolean privatePosition;

    public User toUser() {
        User user = new User();
        bind(user);
        
        return user;
    }
    
    public void bind(User user) {
        user.setCityId(cityId);
        user.setCityPrincipal(cityPrincipal);
        user.setCompany(company);
        user.setPosition(position);
        user.setQq(qq);
        user.setSignature(signature);
        user.setId(id);
        user.setPrivateCompany(privateCompany);
        user.setPrivateEmail(privateEmail);
        user.setPrivatePhone(privatePhone);
        user.setPrivatePosition(privatePosition);
        user.setPrivateQQ(privateQQ);
    }
    
    public Student toStudent() {
        Student student = new Student();
        bind(student);
        
        return student;
    }
    
    public void bind(Student student) {
        student.setClassId(classId);
        student.setEmail(email);
        student.setGender(gender);
        student.setId(id);
        student.setMajor(major);
        student.setName(name);
        student.setPhone(phone);
        student.setUsername(username);
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
    
    public static String encryptPassword(String password) {
        return Md5Crypt.md5Crypt(password.getBytes());
    }
    
    public static boolean validatePassword(String storedPassword, String password) {
        if (null == storedPassword || null == password) {
            return false;
        }
        return storedPassword.equals(Md5Crypt.md5Crypt(password.getBytes(), storedPassword));
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public boolean isCityPrincipal() {
        return cityPrincipal;
    }

    public void setCityPrincipal(boolean cityPrincipal) {
        this.cityPrincipal = cityPrincipal;
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
    
    public String toString() {
        return name;
    }
}