package cn.edu.zju.isst.form;

import org.hibernate.validator.constraints.NotBlank;

import cn.edu.zju.isst.entity.Activity;

public class CityActivityForm extends CityUserActivityForm {
    @NotBlank(message = "发布者不能为空")
    private String poster;
    
    public CityActivityForm() {
        super();
    }
    
    public CityActivityForm(Activity activity) {
        super(activity);
        if (null != activity.getUser()) {
            poster = activity.getUser().getUsername();
        }
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}