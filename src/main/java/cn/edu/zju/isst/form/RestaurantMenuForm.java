package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.RestaurantMenu;

public class RestaurantMenuForm {
    private int id;
    
    @NotEmpty(message = "菜单名不能为空")
    private String name;
    
    private int restaurantId;
    
    private MultipartFile pictureFile;
    
    private String picture;
    
    @NotEmpty(message = "价格不能为空")
    private double price;
    
    @NotEmpty(message = "描述不能为空")
    private String description;
    
    public RestaurantMenuForm() {
        
    }
    
    public RestaurantMenuForm(int restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public RestaurantMenuForm(RestaurantMenu restaurantMenu) {
        id = restaurantMenu.getId();
        restaurantId = restaurantMenu.getRestaurantId();
        name = restaurantMenu.getName();
        price = restaurantMenu.getPrice();
        description = restaurantMenu.getDescription();
        picture = restaurantMenu.getPicture();
    }
    
    public RestaurantMenu build() {
        RestaurantMenu restaurantMenu = new RestaurantMenu();
        restaurantMenu.setRestaurantId(restaurantId);
        bind(restaurantMenu);
        
        return restaurantMenu;
    }
    
    public void bind(RestaurantMenu restaurantMenu) {
        restaurantMenu.setName(name);
        restaurantMenu.setPrice(price);
        String picturePath = WebUtils.saveUploadedFile(pictureFile);
        if (null != picturePath) {
            String oldPicturePath = restaurantMenu.getPicturePath();
            if (null != oldPicturePath) {
                WebUtils.deleteUploadedFile(oldPicturePath);
            }
            restaurantMenu.setPicturePath(picturePath);
        }
        restaurantMenu.setDescription(description);
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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}