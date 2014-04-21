package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.City;

public class CityForm {
    private int id;
    
    @NotBlank(message = "城市名称不能为空")
    private String name;
    
    private int userId;
    
    private int status = City.STATUS_PUBLISHED;

    public CityForm() {
        
    }
    
    public CityForm(City city) {
        id = city.getId();
        name = city.getName();
        userId = city.getUserId();
        status = city.getStatus();
    }
    
    public City build() {
        City city = new City();
        bind(city);
        
        return city;
    }
    
    public void bind(City city) {
        city.setName(name);
        city.setStatus(status);
        city.setUserId(userId);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}