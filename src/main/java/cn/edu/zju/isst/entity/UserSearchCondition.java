package cn.edu.zju.isst.entity;

public class UserSearchCondition {
    private String name;
    private String company;
    private String position;
    private int gender;
    private String grade;
    private int classId;
    private String className;
    private String major;
    private int cityId;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getGender() {
        return gender;
    }
    
    public void setGender(int gender) {
        this.gender = gender;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}