package cn.edu.zju.isst.form;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Restaurant;

public class RestaurantForm {
    private int id;
    
    @NotEmpty(message = "餐馆名称不能为空")
    private String name;
    
    @NotEmpty(message = "地址不能为空")
    private String address;
    
    @NotEmpty(message = "订餐电话不能为空")
    private String hotline;
    
    @NotEmpty(message = "营业时间不能为空")
    private String businessHours;
    
    private MultipartFile pictureFile;
    
    private String picture;
    
    @NotEmpty(message = "餐馆描述不能为空")
    private String description;
    
    public RestaurantForm() {
        
    }
    
    public RestaurantForm(Restaurant restaurant) {
        id = restaurant.getId();
        name = restaurant.getName();
        address = restaurant.getAddress();
        hotline = restaurant.getHotline();
        businessHours = restaurant.getBusinessHours();
        description = restaurant.getDescription();
        picture = restaurant.getPicture();
    }
    
    public Restaurant build(HttpServletRequest request) {
        Restaurant restaurant = new Restaurant();
        bind(request, restaurant);
        
        return restaurant;
    }
    
    public void bind(HttpServletRequest request, Restaurant restaurant) {
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setHotline(hotline);
        restaurant.setBusinessHours(businessHours);
        String picturePath = WebUtils.saveUploadedFile(pictureFile);
        if (null != picturePath) {
            String oldPicturePath = restaurant.getPicturePath();
            if (null != oldPicturePath) {
                WebUtils.deleteUploadedFile(oldPicturePath);
            }
            restaurant.setPicturePath(picturePath);
        }
        restaurant.setDescription(description);
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getHotline() {
        return hotline;
    }
    
    public void setHotline(String hotline) {
        this.hotline = hotline;
    }
    
    public String getBusinessHours() {
        return businessHours;
    }
    
    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }
    
    public String getPicture() {
        return picture;
    }
    
    public MultipartFile getPictureFile() {
        return pictureFile;
    }
    
    public void setPictureFile(MultipartFile pictureFile) {
        this.pictureFile = pictureFile;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}