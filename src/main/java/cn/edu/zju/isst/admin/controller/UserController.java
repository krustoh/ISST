package cn.edu.zju.isst.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.service.CityService;
import cn.edu.zju.isst.service.UserService;
import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.Klass;
import cn.edu.zju.isst.entity.Major;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.form.AlumniForm;
import cn.edu.zju.isst.identity.RequireAdministrator;

@RequireAdministrator(Administrator.ADMIN_ALUMNI)
@Controller("adminUserController")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    
    @RequestMapping("/synchronizeUsers")
    public String synchronizeUsers() {
        userService.synchronizeUsers();
        return "redirect:index.html";
    }
    
    @RequestMapping("/alumni.html")
    public String list(
            UserSearchCondition condition, 
            Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        
        model.addAttribute("users", userService.findAll(condition, 10, page));
        return "users/list";
    }
    
    @RequestMapping(value = "/alumni/{id}.html", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("classes", userService.findAllClasses());
        model.addAttribute("majors", userService.findAllMajors());
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("alumniForm", new AlumniForm(userService.find(id)));
        
        return "users/form";
    }
    
    @RequestMapping(value = "/alumni/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @Valid AlumniForm form, 
            BindingResult result, 
            @PathVariable("id") int id, 
            Model model) {
        form.setId(id);
        boolean hasErrors = false;
        
        if (result.hasErrors()) {
            hasErrors = true;
        } else {
            if (userService.checkUsername(form.getUsername(), form.getId())) {
                result.addError(new ObjectError("username", "用户已经存在"));
                hasErrors = true;
            }
        }
        
        if (hasErrors) {
            model.addAttribute("classes", userService.findAllClasses());
            model.addAttribute("majors", userService.findAllMajors());
            model.addAttribute("cities", cityService.findAllForSelect());
            model.addAttribute("alumniForm", form);
            return "users/form";
        } else {
            User user = userService.find(id);
            form.bind(user);
            userService.save(user);
            WebUtils.addSuccessFlashMessage(String.format("校友 <i>%s</i> 的帐号信息保存成功", form.getName()));
            return WebUtils.redirectAdminUrl("alumni");
        }
    }
    
    @RequestMapping("/alumni/{id}/view.html")
    public String view(@PathVariable("id") int id, Model model) {
        User user = userService.find(id);
        
        if (user.getClassId() > 0) {
            Klass klass = userService.findClass(user.getClassId());
            if (klass != null) {
                user.setClassName(klass.getName());
            }
        }
        
        if (user.getMajorId() > 0) {
            Major major = userService.findMajor(user.getMajorId());
            if (null != major) {
                user.setMajorName(major.getName());
            }
        }
        
        if (user.getCityId() > 0) {
            City city = cityService.find(user.getCityId());
            if (null != city) {
                user.setCityName(city.getName());
            }
        }
        
        model.addAttribute("alumnus", user);
        
        return "users/view";
    }
}