package cn.edu.zju.isst.web.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.common.PaginationList;
import cn.edu.zju.isst.common.Result;
import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Activity;
import cn.edu.zju.isst.entity.ActivitySearchCondition;
import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.form.CityUserActivityForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ActivityService;
import cn.edu.zju.isst.service.CityService;
import cn.edu.zju.isst.service.UserService;

@RequireUser
@Controller("webActivityController")
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;

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
    
    @RequestMapping("/activities.html")
    public String list(Model model,
            ActivitySearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            HttpSession session) {
        condition.setStatus(Activity.STATUS_PUBLISHED);
        if (condition.getCityId() <= 0) {
            StudentUser user = (StudentUser) session.getAttribute("user");
            if (user.getCityId() > 0) {
                condition.setCityId(user.getCityId());
                if (condition.getUserId() == 0) {
                    condition.setUserId(-1);
                }
                model.addAttribute("activities", activityService.findUserList(user.getId(), condition, 10, page));
            } else {
                model.addAttribute("activities", new PaginationList<Activity>(page, 10, 0, new ArrayList<Activity>()));
            }
        }
        model.addAttribute("condition", condition);
        return "activities/list";
    }
    
    @RequestMapping("/activities/auditing.html")
    public String auditingList(Model model,
            ActivitySearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        
        if (user.getCityId() > 0) {
            City city = cityService.find(user.getCityId());
            if (null != city && city.getUserId() == user.getId()) {
                condition.setStatus(Activity.STATUS_HIDDEN);
                condition.setCityId(user.getCityId());
                model.addAttribute("condition", condition);
                model.addAttribute("activities", activityService.findAll(condition, 10, page));
                return "activities/auditing/list";
            }
        }
        
        WebUtils.addErrorFlashMessage("您无权限访问");
        return WebUtils.redirectUrl("/users/activities.html");
    }
    
    @RequestMapping(value = "/activities/audit")
    public String audit(@RequestParam("id[]") String[] ids, HttpSession session) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }

        StudentUser user = (StudentUser) session.getAttribute("user");
        
        if (!idset.isEmpty()) {
            boolean permit = true;
            City city = cityService.find(user.getCityId());
            if (null == city || city.getUserId() != user.getId()) {
                permit = false;
            }
            
            if (permit) {
                for (Activity activity : activityService.findAll(idset)) {
                    if (activity.getCityId() != city.getId()) {
                        permit = false;
                        break;
                    }
                }
            }
            
            if (!permit) {
                WebUtils.addErrorFlashMessage("您无权限审核");
                return WebUtils.redirectUrl("/users/activities.html");
            }
        }

        int count = activityService.publish(idset);
        WebUtils.addSuccessFlashMessage(String.format("成功审核 <i>%d</i> 个活动", count));
        return WebUtils.redirectUrl("/activities/auditing.html");
    }
    
    @RequestMapping("/users/activities.html")
    public String userList(Model model,
            ActivitySearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        condition.setUserId(user.getId());
        model.addAttribute("condition", condition);
        model.addAttribute("activities", activityService.findAll(condition, 10, page));
        
        return "activities/users/list";
    }
    
    @RequestMapping("/users/activities/delete")
    public String delete(@RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            @PathVariable("cityId") int cityId,
            Model model,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (confirm == 0) {
            model.addAttribute("entities", activityService.findAll(idset));
            model.addAttribute("navigationActiveKey", "users_activities");
            model.addAttribute("cancelUrl", WebUtils.createUrl("/users/activities.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                Activity activity = activityService.find(Integer.valueOf(ids[0]).intValue());
                if (null != activity) {
                    if (activity.getUserId() != user.getId()) {
                        WebUtils.addErrorFlashMessage("您无权限删除");
                    } else {
                        activityService.delete(activity);
                        WebUtils.deleteUploadedFile(activity.getPicturePath());
                        WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", activity));
                    }
                } else {
                    WebUtils.addErrorFlashMessage("活动不存在或已被删除");
                }
            } else {
                List<Activity> activities = activityService.findAll(idset);
                for (Activity activity : activities) {
                    if (activity.getUserId() != user.getId()) {
                        WebUtils.addErrorFlashMessage("您无权限删除");
                        return WebUtils.redirectUrl("/users/activities.html");
                    }
                }
                for (Activity activity : activities) {
                    WebUtils.deleteUploadedFile(activity.getPicturePath());
                }
                int count = activityService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除<i>%d</i>个活动", count));
            }
        }
        return WebUtils.redirectUrl("/users/activities.html");
    }
    
    @RequestMapping("/activities/{id}/view.html")
    public String view(Model model, @PathVariable("id") int id, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("activity", activityService.find(id));
        model.addAttribute("participants",  activityService.findUserParticipatedList(user.getId(), 0, 0));
        return "activities/view";
    }
    
    @RequestMapping("/activities/add.html")
    public String add(Model model) {
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("cityUserActivityForm", new CityUserActivityForm());
        return "activities/form";
    }
    
    @RequestMapping(value = "/activities/add.html", method = RequestMethod.POST)
    public String addSave(
            @Valid CityUserActivityForm form, BindingResult result,
            Model model,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        
        if (!result.hasErrors()) {
            form.validate(result);
        }
        
        boolean hasErrors = result.hasErrors();
        if (!hasErrors && user.getCityId() == 0) {
            WebUtils.addErrorFlashMessage("您所在的城市为<i>其他</i>，不能发布活动");
            hasErrors = true;
        }
        
        if (hasErrors) {
            model.addAttribute("cities", cityService.findAllForSelect());
            model.addAttribute("cityUserActivityForm", form);
            return "activities/form";
        }
        
        form.setStatus(Activity.STATUS_HIDDEN);
        form.setUserId(user.getId());
        form.setCityId(user.getCityId());
        
        return save(form);
    }
    
    @RequestMapping("/activities/{id}.html")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("cityUserActivityForm", new CityUserActivityForm(activityService.find(id)));
        return "activities/form";
    }
    
    @RequestMapping(value = "/activities/{id}.html", method = RequestMethod.POST)
    public String editSave(
            @Valid CityUserActivityForm form, BindingResult result,
            Model model, 
            @PathVariable("id") int id,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        
        if (!result.hasErrors()) {
            form.validate(result);
        }
        
        boolean hasErrors = result.hasErrors();
        if (!hasErrors && user.getCityId() == 0) {
            WebUtils.addErrorFlashMessage("您所在的城市为<i>其他</i>，不能发布活动");
            hasErrors = true;
        }
        
        if (hasErrors) {
            model.addAttribute("cities", cityService.findAllForSelect());
            model.addAttribute("cityUserActivityForm", form);
            return "activities/form";
        }

        form.setStatus(Activity.STATUS_HIDDEN);
        form.setUserId(user.getId());
        form.setCityId(user.getCityId());
        
        return save(form);
    }
    
    private String save(CityUserActivityForm form) {
        Activity activity;
        if (form.getId() > 0) {
            activity = activityService.find(form.getId());
            form.bind(activity);
        } else {
            activity = form.build();
        }
        
        activityService.save(activity);
        WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", activity));
        
        return WebUtils.redirectUrl("/users/activities.html");
    }

    @RequestMapping("/users/activities/participated.html")
    public  String userParticipatedList(
            Model model,
            HttpSession session, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        model.addAttribute("activities", activityService.findUserParticipatedList(user.getId(), 10, page));
        
        return "activities/users/participated";
    }
    
    @RequestMapping("/activities/{activityId}/participants.html")
    public String participantList(
            Model model,
            @PathVariable("activityId") int activityId,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        Activity activity = activityService.find(activityId);
        model.addAttribute("activity", activity);
        model.addAttribute("city", cityService.find(activity.getCityId()));
        model.addAttribute("participants", userService.findActivityParticipants(activityId, 20, page));
        
        return "activities/participants";
    }

    @RequestMapping("/activities/{id}/participate")
    public String  participate(@PathVariable("id") int id, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        Result result = activityService.participate(id, user.getId());
        if (result.valid()) {
            WebUtils.addSuccessFlashMessage("报名成功");
        } else {
            WebUtils.addErrorFlashMessage(result);
        }
       
        return WebUtils.redirectUrl("/users/activities/participated.html");
    }
    
    @RequestMapping("/activities/{id}/unparticipate")
    public String unparticipate(@PathVariable("id") int id, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        Result result = activityService.unparticipate(id, user.getId());
        if (result.valid()) {
            WebUtils.addSuccessFlashMessage("取消报名成功");
        } else {
            WebUtils.addErrorFlashMessage(result);
        }
       
        return WebUtils.redirectUrl("/users/activities/participated.html");
    }
}