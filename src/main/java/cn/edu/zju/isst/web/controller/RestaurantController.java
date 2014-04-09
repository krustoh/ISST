package cn.edu.zju.isst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.RestaurantService;

@RequireUser
@Controller
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/restaurants.html", method = RequestMethod.GET)
    public String list(Model model,
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        model.addAttribute("restaurants",restaurantService.findAll(keywords, pageSize, page));
        return "restaurants/list";
    }

    @RequestMapping("/restaurants/{id}.html")
    public String find(@PathVariable("id") int id,
            Model model) {
        model.addAttribute("restaurants",restaurantService.find(id));
        return "restaurants/view";
    }
}
