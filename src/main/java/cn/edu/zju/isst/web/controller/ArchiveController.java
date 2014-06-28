package cn.edu.zju.isst.web.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.ArchiveSearchCondition;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.form.ArchiveForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ArchiveService;
import cn.edu.zju.isst.service.CategoryService;

@RequireUser
@Controller("webArchiveController")
public class ArchiveController {
	@Autowired
	private ArchiveService archiveService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/archives/categories/{categoryAlias}.html", method = RequestMethod.GET)
    public String list(Model model, 
            @PathVariable("categoryAlias") String categoryAlias,
            ArchiveSearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		Category category = categoryService.find(categoryAlias);
        if (null != category) {
            condition.setCategoryId(category.getId());
            condition.setStatus(Archive.STATUS_PUBLISHED);
            model.addAttribute("category", category);
            model.addAttribute("condition", condition);
            model.addAttribute("archives", archiveService.findAll(condition, 10, page));
        } else {
            throw new RuntimeException("Category does not exist.");
        }
        return "archives/list";
    }
	
    @RequestMapping(value = "/users/archives/{categoryAlias}.html", method = RequestMethod.GET)
    public String userList(Model model, 
            @PathVariable("categoryAlias") String categoryAlias,
            ArchiveSearchCondition condition,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        Category category = categoryService.find(categoryAlias);
        condition.setStatus(Archive.STATUS_PUBLISHED);
        if (null != category) {
            condition.setUserId(user.getId());
            condition.setCategoryId(category.getId());
            model.addAttribute("category", category);
            model.addAttribute("condition", condition);
            model.addAttribute("archives", archiveService.findAll(condition, 10, page));
        } else {
            throw new RuntimeException("Category does not exist.");
        }
        return "archives/list";
    }
    
    @RequestMapping(value = "/users/archives/{categoryAlias}/add.html", method = RequestMethod.GET)
    public String add(@PathVariable("categoryAlias") String categoryAlias, Model model) {
        model.addAttribute("category", categoryService.find(categoryAlias));
        ArchiveForm form = new ArchiveForm();
        model.addAttribute("archiveForm", form);
        return "archives/users/form";
    }
    
    @RequestMapping(value = "/users/archives/{categoryAlias}/{id}.html", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        Archive archive = archiveService.find(id);
        model.addAttribute("category", categoryService.find(archive.getCategoryId()));
        model.addAttribute("archiveForm", new ArchiveForm(archive));
        return "archives/users/form";
    }
    
    @RequestMapping(value = "/users/archives/{categoryAlias}/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @PathVariable("categoryAlias") String categoryAlias, 
            Model model, 
            @Valid ArchiveForm form, 
            BindingResult result, 
            HttpSession session) {
        return save(form, result, categoryService.find(categoryAlias), model, session);
    }
    
    @RequestMapping(value = "/users/archives/{categoryAlias}/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @PathVariable("id") int id, 
            @PathVariable("categoryAlias") String categoryAlias, 
            Model model, 
            @Valid ArchiveForm form, 
            BindingResult result, 
            HttpSession session) {
        form.setId(id);
        return save(form, result, categoryService.find(categoryAlias), model, session);
    }
        
    private String save(ArchiveForm form, BindingResult result, Category category, Model model, HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("archiveForm", form);
            return "archives/users/form";
        } else {
            form.setCategoryId(category.getId());
            form.setUserId(user.getId());
            form.setStatus(Archive.STATUS_HIDDEN);

            Archive archive = null;
            if (form.getId() > 0) {
                archive = archiveService.find(form.getId());
                if (null != archive && archive.getUserId() != user.getId()) {
                    WebUtils.addErrorFlashMessage("您无权限");
                    return WebUtils.redirectUrl("/users/archives/" + category.getAlias() + ".html");
                }
            }

            if (null == archive) {
                archive = form.build();
            } else {
                form.bind(archive);
            }

            archiveService.save(archive);
            WebUtils.addSuccessFlashMessage(String.format("成功保存 <i>%s</i>", archive.getTitle()));
            return WebUtils.redirectUrl("/users/archives/" + category.getAlias() + ".html");
        }
    }
    
    @RequestMapping(value = "/users/archives/categories/{categoryAlias}/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            @PathVariable("categoryAlias") String categoryAlias,
            Model model,
            HttpSession session) {
        StudentUser user = (StudentUser) session.getAttribute("user");
        
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        Category category = categoryService.find(categoryAlias);
        
        if (confirm == 0) {
            model.addAttribute("entities", archiveService.findAll(idset));
            model.addAttribute("navigationActiveKey", "users_archive_" + category.getAlias());
            model.addAttribute("cancelUrl", WebUtils.createUrl("/users/archives/categories/" + category.getAlias() + ".html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                Archive archive = archiveService.find(Integer.valueOf(ids[0]).intValue());
                if (null != archive) {
                    if (user.getId() != archive.getUserId()) {
                        WebUtils.addErrorFlashMessage(String.format("您无权限删除：<i>%s</i>", archive.getTitle()));
                    } else {
                        archiveService.delete(archive);
                        WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", archive.getTitle()));
                    }
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                boolean allow = true;
                for (Archive archive : archiveService.findAll(idset)) {
                    if (user.getId() != archive.getUserId()) {
                        WebUtils.addErrorFlashMessage(String.format("您无权限删除：<i>%s</i>", archive.getTitle()));
                        allow = false;
                        break;
                    } 
                }
                
                if (allow) {
                    int count = archiveService.delete(idset);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 条记录", count));
                }
            }
            
            return WebUtils.redirectUrl("/users/archives/categories/"+category.getAlias()+".html");
        }
    }
	
	@RequestMapping(value = "/archives/{id}.html", method = RequestMethod.GET)
	public String view(@PathVariable("id") int id, Model model) {
		Archive archive = archiveService.find(id);
        model.addAttribute("category", categoryService.find(archive.getCategoryId()));
	    model.addAttribute("archives", archive);
        return "archives/view";
	}
}