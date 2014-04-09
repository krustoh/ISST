package cn.edu.zju.isst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.form.ArchiveForm;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ArchiveService;
import cn.edu.zju.isst.service.CategoryService;

@RequireUser
@Controller
public class ArchiveController {
    @Autowired
    private ArchiveService archiveService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/archives/categories/{categoryAlias}.html", method = RequestMethod.GET)
    public String list(Model model, @PathVariable("categoryAlias") String categoryAlias,
            @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        model.addAttribute("archives", archiveService.findAll(categoryAlias, keywords, 20, page));
        return "archives/list";
    }

    @RequestMapping(value = "/archives/{id}.html", method = RequestMethod.GET)
    public String find(Model model, @PathVariable("id") int id) {
//        Archive archive = archiveService.find(id);
//        model.addAttribute("archiveForm", new ArchiveForm(archive));
//        
//        Category category = categoryService.find(archive.getCategoryId());
//        model.addAttribute("category", category);
//        
//        return "archives/form";
        model.addAttribute("archives", archiveService.find(id));
        return "archives/view";
    }
}