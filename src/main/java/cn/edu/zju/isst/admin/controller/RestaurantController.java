package cn.edu.zju.isst.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.service.RestaurantService;

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
    public String edit(Model model, @RequestParam(value = "id", required = true) int id) {
        
        return "restaurants/form";
    }
}