package cn.edu.zju.isst.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.RestaurantService;

@RequireUser
@Controller("apiRestaurantController")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    
    @RequestMapping("/restaurants")
    public @ResponseBody ApiResponse list(
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return new ApiResponse(restaurantService.findAll(keywords, pageSize, page)); 
    }
    
    @RequestMapping("/restaurants/{id}")
    public @ResponseBody ApiResponse find(@PathVariable("id") int id) {
        return new ApiResponse(restaurantService.find(id));
    }
}