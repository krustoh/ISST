package cn.edu.zju.isst.form;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.Activity;

public class CityActivityForm extends ActivityForm {
    @Min(value = 1, message = "城市不能为空")
    private int cityId;
    private int userId;
    @NotBlank(message = "发布者不能为空")
    private String poster;
    
    public CityActivityForm() {
        super();
    }
    
    public CityActivityForm(Activity activity) {
        super(activity);
        cityId = activity.getCityId();
        userId = activity.getUserId();
        if (null != activity.getUser()) {
            poster = activity.getUser().getUsername();
        }
    }
    
    public void bind(Activity activity) {
        super.bind(activity);
        activity.setCityId(cityId);
        activity.setUserId(userId);
    }
    
    public int getCityId() {
        return cityId;
    }
    
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}