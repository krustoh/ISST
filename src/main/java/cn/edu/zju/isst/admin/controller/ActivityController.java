package cn.edu.zju.isst.admin.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.ActivitySearchCondition;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.form.ActivityForm;
import cn.edu.zju.isst.form.CityActivityForm;
import cn.edu.zju.isst.identity.RequireAdministrator;
import cn.edu.zju.isst.service.ActivityService;
import cn.edu.zju.isst.service.CityService;
import cn.edu.zju.isst.service.UserService;

@RequireAdministrator(Administrator.ADMIN)
@Controller("adminActivityController")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

    @RequestMapping("/campus/activities.html")
    public String campusList(
            ActivitySearchCondition condition, 
            Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        condition.setCityId(0);
        condition.setUserId(0);
        model.addAttribute("condition", condition);
        model.addAttribute("activities", activityService.findAll(condition, 10, page));
        return "activities/campus/list";
    }
    
    @RequestMapping("/cities/{cityId}/activities.html")
    public String list(
            ActivitySearchCondition condition, 
            Model model,
            @PathVariable("cityId") int cityId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        condition.setCityId(cityId);
        model.addAttribute("condition", condition);
        model.addAttribute("activities", activityService.findAll(condition, 10, page));
        model.addAttribute("city", cityService.find(cityId));
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("cityId", cityId);
        
        return "activities/list";
    }
    
    @RequestMapping(value = "/campus/activities/{id}.html", method = RequestMethod.GET)
    public String campusEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("activityForm", new ActivityForm(activityService.find(id)));
        return "activities/campus/form";
    }
    
    @RequestMapping(value = "/activities/{id}.html", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        Activity activity = activityService.find(id);
        model.addAttribute("cityActivityForm", new CityActivityForm(activity));
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("city", cityService.find(activity.getCityId()));
        model.addAttribute("cityId", activity.getCityId());
        return "activities/form";
    }
    
    private String saveCityActivity(CityActivityForm form, BindingResult result, Model model, String template, String redirectUrl) {
        if (!result.hasErrors()) {
            form.validate(result);
        }
        
        if (!result.hasErrors()) {
            String poster = form.getPoster().trim();
            StudentUser user = userService.find(poster);
            if (null != user) {
                form.setUserId(user.getId());
            } else {
                result.rejectValue("poster", "poster.not_exists", "发布者不存在");
            }
        }
        
        if (result.hasErrors()) {
            model.addAttribute("cities", cityService.findAllForSelect());
            model.addAttribute("cityId", form.getCityId());
            model.addAttribute("city", cityService.find(form.getCityId()));
            model.addAttribute("cityActivityForm", form);
            return template;
        }

        save(form);
        
        return WebUtils.redirectUrl(redirectUrl);
    }
    
    private String saveCampusActivity(ActivityForm form, BindingResult result, Model model, String template, String redirectUrl) {
        if (!result.hasErrors()) {
            form.validate(result);
        }
        
        if (result.hasErrors()) {
            model.addAttribute("activityForm", form);
            return template;
        }
        
        save(form);
        
        return WebUtils.redirectUrl(redirectUrl);
    }
    
    private void save(ActivityForm form) {
        Activity activity;
        if (form.getId() > 0) {
            activity = activityService.find(form.getId());
            form.bind(activity);
        } else {
            activity = form.build();
        }
        
        activityService.save(activity);
        WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", activity));
    }

    @RequestMapping(value = "/activities/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @Valid CityActivityForm form, 
            BindingResult result, 
            Model model, 
            @PathVariable("id") int id) {
        form.setId(id);
        return saveCityActivity(form, result, model, "activities/form", "/cities/" + form.getCityId() + "/activities.html");
    }

    @RequestMapping(value = "/campus/activities/{id}.html", method = RequestMethod.POST)
    public String saveCampusEdit(
            @Valid ActivityForm form, 
            BindingResult result, 
            Model model, 
            @PathVariable("id") int id) {
        form.setId(id);
        return saveCampusActivity(form, result, model, "activities/campus/form", "/campus/activities.html");
    }
    
    @RequestMapping(value = "/campus/activities/add.html", method = RequestMethod.GET)
    public String campusAdd(Model model) {
        model.addAttribute("activityForm", new ActivityForm());
        return "activities/campus/form";
    }
    
    @RequestMapping(value = "/cities/{cityId}/activities/add.html", method = RequestMethod.GET)
    public String Add(Model model, @PathVariable("cityId") int cityId) {
        CityActivityForm form = new CityActivityForm();
        form.setCityId(cityId);
        model.addAttribute("cityActivityForm", form);
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("city", cityService.find(cityId));
        model.addAttribute("cityId", cityId);
        return "activities/form";
    }

    @RequestMapping(value = "/campus/activities/add.html", method = RequestMethod.POST)
    public String saveCampusAdd(
            @Valid ActivityForm form, 
            BindingResult result, 
            Model model) {
        return saveCampusActivity(form, result, model, "activities/campus/form", "/campus/activities.html");
    }

    @RequestMapping(value = "/cities/{cityId}/activities/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @Valid CityActivityForm form, 
            BindingResult result, 
            Model model, 
            @PathVariable("cityId") int cityId) {
        form.setCityId(cityId);
        return saveCityActivity(form, result, model, "activities/form", "/cities/" + cityId + "/activities.html");
    }
    
    @RequestMapping(value = "/campus/activities/delete")
    public String campusDelete(@RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            Model model) {
        return delete(ids, confirm, model, "/campus/activities.html", "campus_activity");
    }
    
    @RequestMapping(value = "/cities/{cityId}/activities/delete")
    public String delete(@RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            @PathVariable("cityId") int cityId,
            Model model) {
        return delete(ids, confirm, model, "/cities/" + cityId + "/activities.html", "activity");
    }
    
    private String delete(String[] ids, int confirm, Model model, String redirectUrl, String navigationActiveKey) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (confirm == 0) {
            model.addAttribute("entities", activityService.findAll(idset));
            model.addAttribute("navigationActiveKey", navigationActiveKey);
            model.addAttribute("cancelUrl", WebUtils.createUrl(redirectUrl));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                Activity activity = activityService.find(Integer.valueOf(ids[0]).intValue());
                if (null != activity) {
                    activityService.delete(activity);
                    WebUtils.deleteUploadedFile(activity.getPicturePath());
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", activity));
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                for (Activity activity : activityService.findAll(idset)) {
                    WebUtils.deleteUploadedFile(activity.getPicturePath());
                }
                int count = activityService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除<i>%d</i>条记录", count));
            }
        }
        return WebUtils.redirectUrl(redirectUrl);
    }
    
    @RequestMapping(value = "/cities/{cityId}/activities/publish")
    public String publish(@RequestParam("id[]") String[] ids, @PathVariable("cityId") int cityId) {
        return publish(ids, "/cities/" + cityId + "/activities.html");
    }
    
    @RequestMapping(value = "/campus/activities/publish")
    public String campusPublish(@RequestParam("id[]") String[] ids) {
        return publish(ids, "/campus/activities.html");
    }
    
    private String publish(String[] ids, String redirectUrl) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        int count = 0;
        if (!idset.isEmpty()) {
            count = activityService.publish(idset);
        }
        
        WebUtils.addSuccessFlashMessage(String.format("成功发布 <i>%d</i> 条记录", count));
        return WebUtils.redirectUrl(redirectUrl);
    }
    
    @RequestMapping(value = "/campus/activities/hide")
    public String campusHide(@RequestParam("id[]") String[] ids) {
        return hide(ids, "/campus/activities.html");
    }
    
    @RequestMapping(value = "/cities/{cityId}/activities/hide")
    public String hide(@RequestParam("id[]") String[] ids, @PathVariable("cityId") int cityId) {
        return hide(ids, "/cities/" + cityId + "/activities.html");
    }
    
    private String hide(String[] ids, String redirectUrl) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        int count = 0;
        if (!idset.isEmpty()) {
            count = activityService.hide(idset);
        }
        
        WebUtils.addSuccessFlashMessage(String.format("成功隐藏 <i>%d</i> 条记录", count));
        return WebUtils.redirectUrl(redirectUrl);
    }
    
    @RequestMapping("/cities/{cityId}/activities/{activityId}/participants.html")
    public String participantList(
            Model model,
            @PathVariable("cityId") int cityId,
            @PathVariable("activityId") int activityId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("activity", activityService.find(activityId));
        model.addAttribute("participants", userService.findActivityParticipants(activityId, 20, page));
        
        return "activities/participants";
    }
}