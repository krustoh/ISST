package cn.edu.zju.isst.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ActivityService;

@RequireUser
@Controller("apiActivityController")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    
    @RequestMapping("/campus/activities")
    public @ResponseBody ApiResponse campusList(
            ActivitySearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        condition.setCityId(0);
        condition.setUserId(0);
        condition.setStatus(Activity.STATUS_PUBLISHED);
        return new ApiResponse(activityService.findAll(condition, pageSize, page).getItems());
    }
    
    @RequestMapping("/campus/activities/{id}")
    public @ResponseBody ApiResponse find(@PathVariable("id") int id) {
        return new ApiResponse(activityService.find(id));
    }
}