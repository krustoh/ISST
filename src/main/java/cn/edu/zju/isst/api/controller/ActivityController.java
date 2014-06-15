package cn.edu.zju.isst.api.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;
import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ActivityService;
import cn.edu.zju.isst.service.CityService;

@RequireUser
@Controller("apiActivityController")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private CityService cityService;
    
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
    public @ResponseBody ApiResponse campusFind(@PathVariable("id") int id) {
        return new ApiResponse(activityService.find(id));
    }
    
    @RequestMapping("/cities/{cityId}/activities")
    public @ResponseBody ApiResponse list(
            ActivitySearchCondition condition,
            @PathVariable("cityId") int cityId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        condition.setStatus(Activity.STATUS_PUBLISHED);
        condition.setCityId(cityId);
        if (condition.getUserId() == 0) {
        	condition.setUserId(-1);
        }
        return new ApiResponse(activityService.findUserList(user.getId(), condition, pageSize, page).getItems());
    }
    
    @RequestMapping("/cities/{cityId}/activities/{id}")
    public @ResponseBody ApiResponse find(@PathVariable("cityId") int cityId, @PathVariable("id") int id, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        return new ApiResponse(activityService.findForUser(user.getId(), id));
    }
    
    @RequestMapping("/cities/{cityId}/activities/{id}/participate")
    public @ResponseBody ApiResponse participate(@PathVariable("cityId") int cityId, @PathVariable("id") int id, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        return new ApiResponse(activityService.participate(id, user.getId()));
    }
    
    @RequestMapping("/cities/{cityId}/activities/{id}/unparticipate")
    public @ResponseBody ApiResponse unparticipate(@PathVariable("cityId") int cityId, @PathVariable("id") int id, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        return new ApiResponse(activityService.unparticipate(id, user.getId()));
    }
    
    @RequestMapping("/cities/{cityId}/activities/auditing")
    public @ResponseBody ApiResponse auditingList(
            ActivitySearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        
        if (user.getCityId() > 0) {
            City city = cityService.find(user.getCityId());
            if (null != city && city.getUserId() == user.getId()) {
                condition.setStatus(Activity.STATUS_HIDDEN);
                return new ApiResponse(activityService.findAll(condition, pageSize, page).getItems());
            }
        }
        
        return new ApiResponse(new ArrayList<Activity>());
    }
    
    @RequestMapping("/cities/{cityId}/activities/{activityId}/audit")
    public @ResponseBody ApiResponse auditingList(@PathVariable("activityId") int activityId, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        
        Activity activity = activityService.find(activityId);
        if (null != activity) {
            City city = cityService.find(user.getCityId());
            if (null != city && city.getUserId() == user.getId() && city.getId() == activity.getCityId()) {
                if (activityService.publish(activityId) > 0) {
                    return new ApiResponse("审核成功");
                } else {
                    return new ApiResponse("已审核");
                }
            }
        }
        
        return new ApiResponse(5, "您无权限");
    }
}