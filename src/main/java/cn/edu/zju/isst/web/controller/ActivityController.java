package cn.edu.zju.isst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ActivityService;
import cn.edu.zju.isst.service.CityService;

@RequireUser
@Controller("webActivityController")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/campus/activities.html", method = RequestMethod.GET)
    public String campusList(Model model,
            ActivitySearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        condition.setStatus(Activity.STATUS_PUBLISHED);
        condition.setCityId(0);
        condition.setUserId(0);
        model.addAttribute("condition", condition);
        model.addAttribute("activities", activityService.findAll(condition, 10, page));
        return "activities/campus/list";
    }

    @RequestMapping(value = "/campus/activities/{id}.html", method = RequestMethod.GET)
    public String campusView(Model model, @PathVariable("id") int id) {
        model.addAttribute("campus", activityService.find(id));
        return "activities/campus/view";
    }
}