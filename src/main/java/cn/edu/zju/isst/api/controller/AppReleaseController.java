package cn.edu.zju.isst.api.controller;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.identity.RequireUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.service.AppReleaseService;

@RequireUser
@Controller("apiAppReleaseController")
public class AppReleaseController {
    @Autowired
    private AppReleaseService appReleaseService;
    
    @RequestMapping("/android/version")
    public @ResponseBody ApiResponse getAndroidLatestVersion() {
        return new ApiResponse(appReleaseService.getLatestVersion());
    }
}