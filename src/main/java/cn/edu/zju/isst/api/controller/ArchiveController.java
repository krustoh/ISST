package cn.edu.zju.isst.api.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.ArchiveSearchCondition;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.form.ArchiveForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ArchiveService;
import cn.edu.zju.isst.service.CategoryService;

@RequireUser
@Controller("apiArchiveController")
public class ArchiveController {
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping(value = "/archives/categories/{categoryAlias}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse findAll(
            @PathVariable("categoryAlias") String categoryAlias, 
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        ArchiveSearchCondition condition = new ArchiveSearchCondition();
        condition.setCategoryId(categoryService.find(categoryAlias).getId());
        condition.setKeywords(keywords);
        condition.setStatus(Archive.STATUS_PUBLISHED);
        return new ApiResponse(archiveService.findAll(condition, pageSize, page).getItems());
    }
    
    @RequestMapping(value = "/users/archives/{categoryAlias}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse userList(
            @PathVariable("categoryAlias") String categoryAlias,
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        ArchiveSearchCondition condition = new ArchiveSearchCondition();
        condition.setUserId(user.getId());
        condition.setCategoryId(categoryService.find(categoryAlias).getId());
        condition.setKeywords(keywords);
        return new ApiResponse(archiveService.findAll(condition, pageSize, page).getItems());
    }
    
    @RequestMapping(value = "/archives/{id}", method = RequestMethod.GET)
    public @ResponseBody ApiResponse find(
            @PathVariable("id") int id) {
        return new ApiResponse(archiveService.find(id));
    }
    
    @RequestMapping(value = "/users/archives/experience", method = RequestMethod.POST)
    public @ResponseBody ApiResponse post(@Valid ArchiveForm form,  BindingResult result, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        if (result.hasErrors()) {
            return new ApiResponse(result);
        } else {
            form.setCategoryId(3);
            form.setUserId(user.getId());
            form.setStatus(Archive.STATUS_HIDDEN);
            
            Archive archive = null;
            if (form.getId() > 0) {
                archive = archiveService.find(form.getId());
                if (null != archive && archive.getUserId() != user.getId()) {
                    return new ApiResponse(20, "您无权限");
                }
            }
            
            if (null == archive) {
                archive = form.build();
            } else {
                form.bind(archive);
            }
            
            archiveService.save(archive);
            return new ApiResponse(archive);
        }
    }
}