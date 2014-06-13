package cn.edu.zju.isst.form;

import cn.edu.zju.isst.entity.Activity;

public class CityUserActivityForm extends ActivityForm {
    private int cityId;
    private int userId;
    
    public CityUserActivityForm() {
        super();
    }
    
    public CityUserActivityForm(Activity activity) {
        super(activity);
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
}