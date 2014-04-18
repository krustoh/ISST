package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.StudentUser;

public class StudentUserForm extends AlumniForm {
    @NotBlank(message = "学号不能为空")
    private String username;
    @NotBlank(message = "姓名不能为空")
    private String password;
    private String name;
    private String major;
    private int gender;
    private int grade;
    private int classId;
    
    public StudentUserForm() {
        super();
    }
    
    public StudentUserForm(StudentUser user) {
        super(user);

        major = user.getMajor();
        gender = user.getGender();
        name = user.getName();
        username = user.getUsername();
        grade = user.getGrade();
        classId = user.getClassId();
    }
    
    public void bind(StudentUser user) {
        super.bind(user);
        user.setUsername(username);
        user.setGrade(grade);
        user.setClassId(classId);
        user.setMajor(major);
        user.setGender(gender);
        user.setName(name);
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
    
}
