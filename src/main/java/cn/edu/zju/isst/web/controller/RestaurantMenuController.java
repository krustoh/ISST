package cn.edu.zju.isst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.entity.RestaurantMenu;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.RestaurantMenuService;
import cn.edu.zju.isst.service.RestaurantService;

@RequireUser
@Controller("webRestaurantMenuController")
public class RestaurantMenuController {
    @Autowired
    private RestaurantMenuService restaurantMenuService;
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping("/restaurants/{restaurantId}/menus.html")
    public String list(Model model, @PathVariable("restaurantId") int restaurantId, @RequestParam("page") int page) {
        model.addAttribute("restaurant", restaurantService.find(restaurantId));
        model.addAttribute("restaurantMenus", restaurantMenuService.findAll(restaurantId, 10, page));
        return "restaurants/menus/list"; 
    }
    
    @RequestMapping(value = "/restaurants/menus/{id}.html", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id") int id) {
        RestaurantMenu restaurantMenu = restaurantMenuService.find(id);
        model.addAttribute("restaurant", restaurantService.find(restaurantMenu.getRestaurantId()));
        model.addAttribute("restaurantMenu", restaurantMenu);
        return "restaurants/menus/view";
    }
}