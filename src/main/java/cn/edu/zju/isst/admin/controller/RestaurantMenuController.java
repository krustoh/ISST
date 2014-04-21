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
import cn.edu.zju.isst.entity.RestaurantMenu;
import cn.edu.zju.isst.form.RestaurantMenuForm;
import cn.edu.zju.isst.identity.RequireAdministrator;
import cn.edu.zju.isst.service.RestaurantMenuService;
import cn.edu.zju.isst.service.RestaurantService;

@RequireAdministrator(Administrator.ADMIN)
@Controller("adminRestaurantMenuController")
public class RestaurantMenuController {
    @Autowired
    private RestaurantMenuService restaurantMenuService;
    @Autowired
    private RestaurantService restaurantService;
    
    @RequestMapping(value = "/restaurants/{restaurantId}/menus.html", method = RequestMethod.GET)
    public String list(
            Model model,
            @PathVariable("restaurantId") int restaurantId,
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("restaurant", restaurantService.find(restaurantId));
        model.addAttribute("restaurantMenus", restaurantMenuService.findAll(restaurantId, 10, page));
        return "restaurants/menus/list"; 
    }
    
    @RequestMapping(value = "/restaurants/menus/{id}.html", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        RestaurantMenu restaurantMenu = restaurantMenuService.find(id);
        model.addAttribute("restaurant", restaurantService.find(restaurantMenu.getRestaurantId()));
        model.addAttribute("restaurantMenuForm", new RestaurantMenuForm(restaurantMenu));
        return "restaurants/menus/form";
    }

    @RequestMapping(value = "/restaurants/menus/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @Valid RestaurantMenuForm form, 
            BindingResult result, 
            Model model, 
            @PathVariable("id") int id) {
        form.setId(id);
        RestaurantMenu restaurantMenu = restaurantMenuService.find(id);
        if (result.hasErrors()) {
            model.addAttribute("restaurant", restaurantService.find(restaurantMenu.getRestaurantId()));
            model.addAttribute("restaurantMenuForm", form);
            return "restaurants/menus/form";
        } else {
            form.bind(restaurantMenu);
            restaurantMenuService.save(restaurantMenu);
            WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", restaurantMenu.getName()));
            return WebUtils.redirectUrl("/restaurants/"+restaurantMenu.getRestaurantId()+"/menus.html");
        }
    }
    

    @RequestMapping(value = "/restaurants/{restaurantId}/menus/add.html", method = RequestMethod.GET)
    public String add(Model model, @PathVariable("restaurantId") int restaurantId) {
        model.addAttribute("restaurant", restaurantService.find(restaurantId));
        model.addAttribute("restaurantMenuForm", new RestaurantMenuForm(restaurantId));
        return "restaurants/menus/form";
    }

    @RequestMapping(value = "/restaurants/{restaurantId}/menus/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @Valid RestaurantMenuForm form, 
            BindingResult result, 
            Model model, 
            @PathVariable("restaurantId") int restaurantId) {
        form.setRestaurantId(restaurantId);
        if (result.hasErrors()) {
            model.addAttribute("restaurant", restaurantService.find(restaurantId));
            model.addAttribute("restaurantMenuForm", form);
            return "restaurants/menus/form";
        } else {
            RestaurantMenu restaurantMenu = form.build();
            restaurantMenuService.save(restaurantMenu);
            WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", restaurantMenu.getName()));
            return WebUtils.redirectUrl("/restaurants/" + restaurantId + "/menus.html");
        }
    }
    
    @RequestMapping(value = "/restaurants/{restaurantId}/menus/delete")
    public String delete(
            @PathVariable("restaurantId") int restaurantId,
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        if (confirm == 0) {
            model.addAttribute("entities", restaurantMenuService.findAll(idset));
            model.addAttribute("navigationActiveKey", "restaurants");
            model.addAttribute("cancelUrl", WebUtils.createAdminUrl("restaurants/"+restaurantId+"/menus.html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                RestaurantMenu restaurantMenu = restaurantMenuService.find(Integer.valueOf(ids[0]).intValue());
                if (null != restaurantMenu) {
                    restaurantMenuService.delete(restaurantMenu);
                    WebUtils.deleteUploadedFile(restaurantMenu.getPicturePath());
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", restaurantMenu.getName()));
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                for (RestaurantMenu restaurantMenu : restaurantMenuService.findAll(idset)) {
                    WebUtils.deleteUploadedFile(restaurantMenu.getPicturePath());
                }
                int count = restaurantMenuService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除<i>%d</i>条记录", count));
            }
        }
        return WebUtils.redirectUrl("/restaurants/"+restaurantId+"/menus.html");
    }
}