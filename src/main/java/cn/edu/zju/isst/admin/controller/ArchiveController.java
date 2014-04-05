package cn.edu.zju.isst.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.common.FlashMessage;
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
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize) {
        Category category = categoryService.find(categoryAlias);
        if (null == category) {
            model.addAttribute("category", category);
            model.addAttribute("archives", archiveService.findAll(category, keywords, pageSize, page));
        } else {
        }
        
        return "archives/list";
    }
    
    @RequestMapping(value = "/archives/{id}.html", method = RequestMethod.GET)
    public String edit(Model model, @RequestParam(value = "id", required = true) int id) {
        model.addAttribute("archiveForm", new ArchiveForm(archiveService.find(id)));
        return "archives/form";
    }
    
    @RequestMapping(value = "/archives/{id}.html", method = RequestMethod.POST)
    public String saveEdit(@Valid ArchiveForm form, BindingResult result, Model model) {
        Category category = categoryService.find(form.getCategoryId());
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("archiveForm", form);
            return "archives/form";
        } else {
            Archive archive = archiveService.save(form);
            model.addAttribute("flash_message", FlashMessage.success(String.format("成功保存：<i>%s</i>", archive.getTitle())));
            return "redirect:/admin/archives/categories/" + category.getAlias() + ".html";
        }
    }
    
    @RequestMapping(value = "/archives/categories/{categoryAlias}/add.html", method = RequestMethod.GET)
    public String add(Model model, @PathVariable("categoryAlias") String categoryAlias) {
        Category category = categoryService.find(categoryAlias);
        model.addAttribute("category", category);
        model.addAttribute("archiveForm", new ArchiveForm());
        return "archives/form";
    }
    
    @RequestMapping(value = "/archives/categories/{categoryAlias}/add.html", method = RequestMethod.POST)
    public String saveAdd(@Valid ArchiveForm form, BindingResult result, @PathVariable("categoryAlias") String categoryAlias, Model model) {
        Category category = categoryService.find(categoryAlias);
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("archiveForm", form);
            return "archives/form";
        } else {
            Archive archive = archiveService.save(form);
            model.addAttribute("flash_message", FlashMessage.success(String.format("成功保存：<i>%s</i>", archive.getTitle())));
            return "redirect:/admin/archives/categories/" + category.getAlias() + ".html";
        }
    }
}