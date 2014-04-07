package cn.edu.zju.isst.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.RestaurantMenuService;

@RequireUser
@Controller("apiRestaurantMenuController")
public class RestaurantMenuController {
    @Autowired
    private RestaurantMenuService restaurantMenuService;
    
    @RequestMapping("/restaurants/{restaurantId}/menus")
    public @ResponseBody ApiResponse list(@PathVariable("restaurantId") int restaurantId) {
        return new ApiResponse(restaurantMenuService.findAll(restaurantId, 0, 1).getItems()); 
    }
}