package cn.edu.zju.isst.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ActivityService;

@RequireUser
@Controller("apiActivityController")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    
    @RequestMapping("/campus/activities")
    public @ResponseBody ApiResponse campusList(
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return new ApiResponse(activityService.findAllOfCampus(keywords, pageSize, page));
    }
    
    @RequestMapping("/campus/activities/{id}")
    public @ResponseBody ApiResponse find(@PathVariable("id") int id) {
        return new ApiResponse(activityService.find(id));
    }
}