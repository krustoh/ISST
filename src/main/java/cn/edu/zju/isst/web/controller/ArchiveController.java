package cn.edu.zju.isst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.entity.Archive;
import cn.edu.zju.isst.entity.ArchiveSearchCondition;
import cn.edu.zju.isst.entity.Category;
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
		condition.setStatus(Archive.STATUS_PUBLISHED);
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
	
	@RequestMapping(value = "/archives/{id}.html", method = RequestMethod.GET)
	public String view(@PathVariable("id") int id, Model model) {
		Archive archive = archiveService.find(id);
        model.addAttribute("category", categoryService.find(archive.getCategoryId()));
	    model.addAttribute("archives", archive);
        return "archives/view";
	}
}