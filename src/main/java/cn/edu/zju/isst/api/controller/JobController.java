package cn.edu.zju.isst.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.JobService;

@RequireUser()
@Controller("apiJobController")
public class JobController {
    @Autowired
    private JobService jobService;
    
    @RequestMapping(value = "/jobs/categories/{categoryAlias}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse findAll(
            @PathVariable("categoryAlias") String categoryAlias, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return new ApiResponse(jobService.findAll(categoryAlias, pageSize, page));
    }
    
    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse find(@PathVariable("id") int id) {
        return new ApiResponse(jobService.find(id));
    }
}
