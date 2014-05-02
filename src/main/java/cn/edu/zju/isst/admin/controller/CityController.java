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

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.entity.City;
import cn.edu.zju.isst.entity.CitySearchCondition;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.form.CityForm;
import cn.edu.zju.isst.identity.RequireAdministrator;
import cn.edu.zju.isst.service.CityService;
import cn.edu.zju.isst.service.UserService;

@RequireAdministrator(Administrator.ADMIN)
@Controller("adminCityController")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private UserService userService;
    
    @RequestMapping("/cities.html")
    public String list(
            CitySearchCondition condition, 
            Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("condition", condition);
        model.addAttribute("cities", cityService.findAll(condition, 20, page));
        
        return "cities/list";
    }
    
    @RequestMapping(value = "/cities/{id}.html", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        CityForm form = new CityForm(cityService.find(id));
        if (form.getUserId() > 0) {
            StudentUser user = userService.find(form.getUserId());
            if (null != user) {
                form.setPrincipal(user.getUsername());
            }
        }
        model.addAttribute("cityForm", form);
        return "cities/form";
    }
    
    @RequestMapping(value = "/cities/{id}.html", method = RequestMethod.POST)
    public String saveEdit(@Valid CityForm form, 
            BindingResult result, 
            Model model, 
            @PathVariable("id") int id) {
        form.setId(id);
        return save(form, result, model);
    }
    
    @RequestMapping(value = "/cities/add.html", method = RequestMethod.GET)
    public String Add(Model model) {
        CityForm form = new CityForm();
        model.addAttribute("cityForm", form);
        return "cities/form";
    }

    @RequestMapping(value = "/cities/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @Valid CityForm form, 
            BindingResult result, 
            Model model) {
        return save(form, result, model);
    }
    
    private String save(CityForm form, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            if (null == form.getPrincipal() || form.getPrincipal().isEmpty()) {
                form.setUserId(0);
            } else {
                StudentUser user = userService.find(form.getPrincipal());
                if (null != user) {
                    form.setUserId(user.getId());
                } else {
                    result.rejectValue("principal", "principal.not_exists", "城主不存在");
                }
            }
        }
        
        if (result.hasErrors()) {
            model.addAttribute("activityForm", form);
            return "cities/form";
        }

        City city;
        
        if (form.getId() > 0) {
            city = cityService.find(form.getId());
            form.bind(city);
        } else {
            city = form.build();
        }
        
        cityService.save(city);
        WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", city));
        return WebUtils.redirectUrl("/cities.html");
    }
    
    @RequestMapping(value = "/cities/delete")
    public String delete(@RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (confirm == 0) {
            model.addAttribute("entities", cityService.findAll(idset));
            model.addAttribute("navigationActiveKey", "city");
            model.addAttribute("cancelUrl", WebUtils.createUrl("/cities.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                City city = cityService.find(Integer.valueOf(ids[0]).intValue());
                if (null != city) {
                    cityService.delete(city);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", city));
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                int count = cityService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除<i>%d</i>条记录", count));
            }
        }
        return WebUtils.redirectUrl("/cities.html");
    }
    
    @RequestMapping(value = "/cities/publish")
    public String publish(@RequestParam("id[]") String[] ids) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        int count = 0;
        if (!idset.isEmpty()) {
            count = cityService.publish(idset);
        }
        
        WebUtils.addSuccessFlashMessage(String.format("成功发布 <i>%d</i> 条记录", count));
        return WebUtils.redirectUrl("cities.html");
    }
    
    @RequestMapping(value = "/cities/hide")
    public String hide(@RequestParam("id[]") String[] ids) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        int count = 0;
        if (!idset.isEmpty()) {
            count = cityService.hide(idset);
        }
        
        WebUtils.addSuccessFlashMessage(String.format("成功隐藏 <i>%d</i> 条记录", count));
        return WebUtils.redirectUrl("cities.html");
    }
}