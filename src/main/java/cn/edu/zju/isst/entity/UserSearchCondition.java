package cn.edu.zju.isst.entity;

public class UserSearchCondition {
    private String name;
    private String company;
    private int geneder;
    private int grade;
    private int classId;
    private int majorId;
    private int cityId;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getGeneder() {
        return geneder;
    }
    
    public void setGeneder(int geneder) {
        this.geneder = geneder;
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
    
    public int getCityId() {
        return cityId;
    }
    
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    
    public String getCompany() {
        return company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }
}