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

import cn.edu.zju.isst.service.CityService;
import cn.edu.zju.isst.service.UserService;
import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.entity.UserSearchCondition;
import cn.edu.zju.isst.form.StudentUserForm;
import cn.edu.zju.isst.identity.RequireAdministrator;

@RequireAdministrator(Administrator.ADMIN_ALUMNI)
@Controller("adminUserController")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    
    @RequestMapping("/alumni.html")
    public String list(
            UserSearchCondition condition, 
            Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("classes", userService.findAllClasses());
        model.addAttribute("majors", userService.findAllMajors());
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("condition", condition);
        model.addAttribute("users", userService.findAll(condition, 10, page));
        
        return "users/list";
    }
    
    @RequestMapping(value = "/alumni/{id}.html", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("classes", userService.findAllClasses());
        model.addAttribute("majors", userService.findAllMajors());
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("studentUserForm", new StudentUserForm(userService.find(id)));
        
        return "users/form";
    }
    
    @RequestMapping(value = "/alumni/add.html", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("classes", userService.findAllClasses());
        model.addAttribute("majors", userService.findAllMajors());
        model.addAttribute("cities", cityService.findAllForSelect());
        model.addAttribute("studentUserForm", new StudentUserForm());
        
        return "users/form";
    }
    
    @RequestMapping(value = "/alumni/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @Valid StudentUserForm form, 
            BindingResult result, 
            @PathVariable("id") int id, 
            Model model) {
        form.setId(id);
        return save(form, result, model);
    }
    
    @RequestMapping(value = "/alumni/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @Valid StudentUserForm form, 
            BindingResult result, 
            Model model) {
        return save(form, result, model);
    }
    
    private String save(StudentUserForm form, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (userService.checkUsername(form.getUsername(), form.getId())) {
                result.rejectValue("username", "username.exists", "学号已经存在");
            }
        }
        StudentUser user = null;
        if (form.getId() > 0) {
            user = userService.find(form.getId());
            form.bind(user);
        } else {
            user = form.build();
            if (null == user.getPassword() || user.getPassword().trim().length() == 0) {
                result.rejectValue("password", "password.blank", "密码不能为空");
            }
        }
        
        if (result.hasErrors()) {
            model.addAttribute("classes", userService.findAllClasses());
            model.addAttribute("majors", userService.findAllMajors());
            model.addAttribute("cities", cityService.findAllForSelect());
            model.addAttribute("studentUserForm", form);
            return "users/form";
        } else {
            userService.save(user);
            WebUtils.addSuccessFlashMessage(String.format("校友 <i>%s</i> 的帐号信息保存成功", form.getName()));
            return WebUtils.redirectUrl("/alumni/" + user.getId() + "/view.html");
        }
    }
    
    @RequestMapping("/alumni/{id}/view.html")
    public String view(@PathVariable("id") int id, Model model) {
        StudentUser user = userService.find(id);
        model.addAttribute("alumnus", user);
        
        return "users/view";
    }
    
    @RequestMapping(value = "/alumni/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (confirm == 0) {
            model.addAttribute("entities", userService.findAll(idset));
            model.addAttribute("navigationActiveKey", "alumni");
            model.addAttribute("cancelUrl", WebUtils.createUrl("/alumni.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                StudentUser user = userService.find(Integer.valueOf(ids[0]).intValue());
                if (null != user) {
                    userService.delete(user);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", user));
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                int count = userService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 条记录", count));
            }
            
            return WebUtils.redirectUrl("/alumni.html");
        }
    }
}