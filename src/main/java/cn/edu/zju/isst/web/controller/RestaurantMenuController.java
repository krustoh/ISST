package cn.edu.zju.isst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.RestaurantMenuService;

@RequireUser
@Controller
public class RestaurantMenuController {
    @Autowired
    private RestaurantMenuService restaurantMenuService;
    
    @RequestMapping("/restaurants/{restaurantId}/menus.html")
    public String list(Model model,
            @PathVariable("restaurantId") int restaurantId,
            @RequestParam("page") int page) {
        model.addAttribute("restaurants",restaurantMenuService.findAll(restaurantId, 10, page)); 
        return "restaurants/menus/list";
    }
}