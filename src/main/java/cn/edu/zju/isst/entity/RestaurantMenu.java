package cn.edu.zju.isst.entity;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.dao.annotation.Column;
import cn.edu.zju.isst.dao.annotation.Entity;
import cn.edu.zju.isst.dao.annotation.ID;

@Entity("restaurant_menus")
public class RestaurantMenu {
    @ID
    @Column
    private int id;
    
    @Column("restaurant_id")
    private int restaurantId;
    
    @Column
    private String name;
    
    @Column("picture_path")
    private String picturePath;
    
    @Column
    private String description;
    
    @Column
    private double price;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getRestaurantId() {
        return restaurantId;
    }
    
    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPicture() {
        return WebUtils.parseUploadedUrl(picturePath);
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
    
    public String toString() {
    	return getName();
    }
}