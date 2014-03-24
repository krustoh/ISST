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
import cn.edu.zju.isst.service.ArchiveService;

@RequireUser()
@Controller("apiArchiveController")
public class ArchiveController {
    @Autowired
    private ArchiveService archiveService;
    
    @RequestMapping(value = "/archives/categories/{categoryAlias}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse findAll(
            @PathVariable("categoryAlias") String categoryAlias, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        return new ApiResponse(archiveService.findAll(categoryAlias, pageSize, page));
    }
    
    @RequestMapping(value = "/archives/{id}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse find(
            @PathVariable("id") int id) {
        return new ApiResponse(archiveService.find(id));
    }
}
