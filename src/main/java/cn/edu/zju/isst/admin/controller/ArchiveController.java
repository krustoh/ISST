package cn.edu.zju.isst.admin.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.form.ArchiveForm;
import cn.edu.zju.isst.identity.RequireAdministrator;
import cn.edu.zju.isst.service.ArchiveService;
import cn.edu.zju.isst.service.CategoryService;

@RequireAdministrator
@Controller("adminArchiveController")
public class ArchiveController {
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping(value = "/archives/categories/{categoryAlias}.html", method = RequestMethod.GET)
    public String list(Model model,
            @PathVariable("categoryAlias") String categoryAlias, 
            @RequestParam(value = "status", required = false, defaultValue = "-1") int status, 
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        Category category = categoryService.find(categoryAlias);
        if (null != category) {
            model.addAttribute("category", category);
            model.addAttribute("archives", archiveService.findAll(category, status, keywords, 10, page));
        } else {
            throw new RuntimeException("Category does not exist.");
        }
        
        return "archives/list";
    }
    
    @RequestMapping(value = "/archives/{id}.html", method = RequestMethod.GET)
    public String edit(Model model, @RequestParam(value = "id", required = true) int id) {
        Archive archive = archiveService.find(id);
        model.addAttribute("archiveForm", new ArchiveForm(archive));
        
        Category category = categoryService.find(archive.getCategoryId());
        model.addAttribute("category", category);
        
        return "archives/form";
    }
    
    @RequestMapping(value = "/archives/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @Valid ArchiveForm form, 
            BindingResult result, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession session,
            Model model) {
        Category category = categoryService.find(form.getCategoryId());
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("archiveForm", form);
            return "archives/form";
        } else {
            Archive archive = archiveService.save(form.buildArchive());
            WebUtils.addSuccessFlashMessage(session, String.format("成功删除：<i>%s</i>", archive.getTitle()));
            return WebUtils.redirectAdminUrl(request, response, "archives/categories/" + category.getAlias() + ".html");
        }
    }
    
    @RequestMapping(value = "/archives/categories/{categoryAlias}/add.html", method = RequestMethod.GET)
    public String add(Model model, @PathVariable("categoryAlias") String categoryAlias) {
        Category category = categoryService.find(categoryAlias);
        model.addAttribute("category", category);
        
        ArchiveForm form = new ArchiveForm();
        form.setCategoryId(category.getId());
        model.addAttribute("archiveForm", form);
        
        return "archives/form";
    }
    
    @RequestMapping(value = "/archives/categories/{categoryAlias}/add.html", method = RequestMethod.POST)
    public String saveAdd(
            @Valid ArchiveForm form, 
            BindingResult result, 
            @PathVariable("categoryAlias") String categoryAlias,
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model) {
        Category category = categoryService.find(categoryAlias);
        form.setCategoryId(category.getId());
        
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("archiveForm", form);
            return "archives/form";
        } else {
            Archive archive = archiveService.save(form.buildArchive());
            WebUtils.addSuccessFlashMessage(session, String.format("成功删除：<i>%s</i>", archive.getTitle()));
            return WebUtils.redirectAdminUrl(request, response, "archives/categories/" + category.getAlias() + ".html");
        }
    }
    
    @RequestMapping(value = "/archives/categories/{categoryAlias}/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            @PathVariable("categoryAlias") String categoryAlias, 
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        Category category = categoryService.find(categoryAlias);
        
        if (confirm == 0) {
            model.addAttribute("entities", archiveService.findAll(idset));
            model.addAttribute("category", category);
            return "confirm/delete";
        } else {
            if (ids.length == 1) {
                Archive archive = archiveService.find(Integer.valueOf(ids[0]).intValue());
                if (null != archive) {
                    archiveService.delete(archive);
                    WebUtils.addSuccessFlashMessage(session, String.format("成功删除：<i>%s</i>", archive.getTitle()));
                } else {
                    WebUtils.addErrorFlashMessage(session, "记录不存在或已被删除");
                }
            } else {
                int count = archiveService.delete(idset);
                WebUtils.addSuccessFlashMessage(session, String.format("成功删除<i>%d</i>条记录", count));
            }
            
            return WebUtils.redirectAdminUrl(request, response, "archives/categories/"+category.getAlias()+".html");
        }
    }
}