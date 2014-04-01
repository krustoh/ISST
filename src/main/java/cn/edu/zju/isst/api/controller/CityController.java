package cn.edu.zju.isst.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.CityService;

@RequireUser
@Controller("apiCityController")
public class CityController {
    @Autowired
    private CityService cityService;
    
    @RequestMapping("/cities")
    public @ResponseBody ApiResponse findAll() {
        return new ApiResponse(cityService.findAll());
    }
}