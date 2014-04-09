package cn.edu.zju.isst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ActivityService;

@RequireUser
@Controller
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/campus/activities.html", method = RequestMethod.GET)
    public String campusList(Model model,
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        model.addAttribute("campus", activityService.findAllOfCampus(keywords, pageSize, page));
        return "activities/campus/list";
    }

    @RequestMapping(value = "/campus/activities/{id}.html", method = RequestMethod.GET)
    public String find(Model model, @PathVariable("id") int id) {
        model.addAttribute("campus", activityService.find(id));
        return "activities/campus/view";
    }
}