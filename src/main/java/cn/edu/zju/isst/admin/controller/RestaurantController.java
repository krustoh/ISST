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
import cn.edu.zju.isst.entity.Restaurant;
import cn.edu.zju.isst.form.RestaurantForm;
import cn.edu.zju.isst.identity.RequireAdministrator;
import cn.edu.zju.isst.service.RestaurantService;

@RequireAdministrator(Administrator.ADMIN)
@Controller("adminRestaurantController")
public class RestaurantController {
	@Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/restaurants.html", method = RequestMethod.GET)
    public String list(
            Model model,
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("restaurants", restaurantService.findAll(keywords, 20, page));
        return "restaurants/list"; 
    }

    @RequestMapping(value = "/restaurants/{id}.html", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("restaurantForm", new RestaurantForm(restaurantService.find(id)));
        return "restaurants/form";
    }

    @RequestMapping(value = "/restaurants/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @Valid RestaurantForm form, 
            BindingResult result, 
            Model model, 
            @PathVariable("id") int id) {
        form.setId(id);
        if (result.hasErrors()) {
            model.addAttribute("restaurantForm", form);
            return "restaurants/form";
        } else {
            Restaurant restaurant = restaurantService.find(id);
            form.bind(restaurant);
            restaurantService.save(restaurant);
            WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", restaurant.getName()));
            return WebUtils.redirectUrl("/restaurants.html");
        }
    }
    

    @RequestMapping(value = "/restaurants/add.html", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("restaurantForm", new RestaurantForm());
        return "restaurants/form";
    }

    @RequestMapping(value = "/restaurants/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @Valid RestaurantForm form, 
            BindingResult result, 
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("restaurantForm", form);
            return "restaurants/form";
        } else {
            Restaurant restaurant = form.build();
            restaurantService.save(restaurant);
            WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", restaurant.getName()));
            return WebUtils.redirectUrl("/restaurants.html");
        }
    }
    
    @RequestMapping(value = "/restaurants/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (confirm == 0) {
            model.addAttribute("entities", restaurantService.findAll(idset));
            model.addAttribute("navigationActiveKey", "restaurants");
            model.addAttribute("cancelUrl", WebUtils.createUrl("/restaurants.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                Restaurant restaurant = restaurantService.find(Integer.valueOf(ids[0]).intValue());
                if (null != restaurant) {
                    restaurantService.delete(restaurant);
                    WebUtils.deleteUploadedFile(restaurant.getPicturePath());
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", restaurant.getName()));
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                for (Restaurant restaurant : restaurantService.findAll(idset)) {
                    WebUtils.deleteUploadedFile(restaurant.getPicturePath());
                }
                int count = restaurantService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除<i>%d</i>条记录", count));
            }
        }
        return WebUtils.redirectUrl("/restaurants.html");
    }
}