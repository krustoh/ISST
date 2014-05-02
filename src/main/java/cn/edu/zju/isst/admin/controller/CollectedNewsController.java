package cn.edu.zju.isst.admin.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.entity.Category;
import cn.edu.zju.isst.entity.CollectedNews;
import cn.edu.zju.isst.form.CollectedNewsForm;
import cn.edu.zju.isst.identity.RequireAdministrator;
import cn.edu.zju.isst.service.CategoryService;
import cn.edu.zju.isst.service.CollectedNewsService;

@RequireAdministrator(Administrator.ADMIN)
@Controller("adminCollectedNewsController")
public class CollectedNewsController {
    @Autowired
    private CollectedNewsService collectedNewsService;
    @Autowired
    private CategoryService categoryService;
    
    @RequestMapping("/collectedNews/categories/{categoryAlias}.html")
    public String collecting(
            @PathVariable("categoryAlias") String categoryAlias,
            Model model) {
        Category category = categoryService.find(categoryAlias);
        
        model.addAttribute("news", collectedNewsService.findAll(category.getId(), CollectedNews.STATUS_UNPROCESSED, 0, 0));
        model.addAttribute("category", category);
        
        return "collectedNews/collecting";
    }
    
    @RequestMapping("/collectedNews/categories/{categoryAlias}/published.html")
    public String listForPublished(
            @PathVariable("categoryAlias") String categoryAlias,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model) {
        return list(CollectedNews.STATUS_PUBLISHED, "published", categoryAlias, page, model);
    }
    
    @RequestMapping("/collectedNews/categories/{categoryAlias}/ignored.html")
    public String listForIgnored(
            @PathVariable("categoryAlias") String categoryAlias,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model) {
        return list(CollectedNews.STATUS_IGNORED, "ignored", categoryAlias, page, model);
    }
    
    private String list(int status, String statusLabel, String categoryAlias, int page, Model model) {
        Category category = categoryService.find(categoryAlias);
        
        model.addAttribute("news", collectedNewsService.findAll(category.getId(), status, 10, page));
        model.addAttribute("category", category);
        model.addAttribute("status", statusLabel);
        
        return "collectedNews/list";
    }

    @RequestMapping("/collectedNews/categories/{categoryAlias}/collect/archive")
    public @ResponseBody List<CollectedNews> collectForArchive(@PathVariable("categoryAlias") String categoryAlias) {
        Category category = categoryService.find(categoryAlias);
        return collectedNewsService.collectForArchive(category.getId());
    }
    
    @RequestMapping("/collectedNews/categories/{categoryAlias}/collect/job")
    public @ResponseBody List<CollectedNews> collectForJob(@PathVariable("categoryAlias") String categoryAlias) {
        Category category = categoryService.find(categoryAlias);
        return collectedNewsService.collectForJob(category.getId());
    }

    @RequestMapping("/collectedNews/collect/{id}")
    public @ResponseBody CollectedNews collectDetail(@PathVariable("id") int id) {
        return collectedNewsService.collectDetail(id);
    }
    
    @RequestMapping(value = "/collectedNews/{id}.html", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") int id) {
        CollectedNews news = collectedNewsService.find(id);
        model.addAttribute("collectedNewsForm", new CollectedNewsForm(news));
        
        Category category = categoryService.find(news.getCategoryId());
        model.addAttribute("category", category);
        
        return "collectedNews/form";
    }
    
    @RequestMapping(value = "/collectedNews/{id}.html", method = RequestMethod.POST)
    public String saveEdit(
            @Valid CollectedNewsForm form, 
            BindingResult result, 
            @PathVariable("id") int id,
            Model model) {
        form.setId(id);
        CollectedNews news = collectedNewsService.find(id);
        Category category = categoryService.find(news.getCategoryId());
        if (result.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("collectedNewsForm", form);
            return "collectedNews/form";
        } else {
            form.bind(news);
            collectedNewsService.save(news);
            WebUtils.addSuccessFlashMessage(String.format("成功保存：<i>%s</i>", news.getTitle()));
            return WebUtils.redirectUrl("/collectedNews/categories/" + category.getAlias() + ".html");
        }
    }
    
    @RequestMapping(value = "/collectedNews/categories/{categoryAlias}/publish/archive")
    public String publish(
            @RequestParam("id[]") String[] ids,
            @PathVariable("categoryAlias") String categoryAlias,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        int count = collectedNewsService.publishForArchive(idset);
        WebUtils.addSuccessFlashMessage(String.format("成功发布 <i>%d</i> 条记录", count));
        
        Category category = categoryService.find(categoryAlias);
        return WebUtils.redirectUrl("/collectedNews/categories/" + category.getAlias() + ".html");
    }
    
    @RequestMapping(value = "/collectedNews/categories/{categoryAlias}/ignore")
    public String ignore(
            @RequestParam("id[]") String[] ids,
            @PathVariable("categoryAlias") String categoryAlias,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        int count = collectedNewsService.ignore(idset);
        WebUtils.addSuccessFlashMessage(String.format("成功忽略 <i>%d</i> 条记录", count));
        
        Category category = categoryService.find(categoryAlias);
        return WebUtils.redirectUrl("/collectedNews/categories/" + category.getAlias() + ".html");
    }
    
    @RequestMapping(value = "/collectedNews/categories/{categoryAlias}/unprocess")
    public String unprocess(
            @RequestParam("id[]") String[] ids,
            @PathVariable("categoryAlias") String categoryAlias,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        int count = collectedNewsService.unprocess(idset);
        WebUtils.addSuccessFlashMessage(String.format("成功撤销忽略 <i>%d</i> 条记录", count));
        
        Category category = categoryService.find(categoryAlias);
        return WebUtils.redirectUrl("/collectedNews/categories/" + category.getAlias() + "/ignored.html");
    }
    
    @RequestMapping(value = "/collectedNews/categories/{categoryAlias}/delete")
    public String delete(
            @RequestParam("id[]") String[] ids,
            @RequestParam(value = "confirm", required = false, defaultValue = "0") int confirm,
            @PathVariable("categoryAlias") String categoryAlias,
            Model model) {
        Set<Integer> idset = new HashSet<Integer>();
        for (String id : ids) {
            idset.add(Integer.valueOf(id));
        }
        
        Category category = categoryService.find(categoryAlias);
        
        if (confirm == 0) {
            model.addAttribute("entities", collectedNewsService.findAll(idset));
            model.addAttribute("navigationActiveKey", (category.getAlias().equals("campus") ? "archive_" : "job_") + category.getAlias());
            model.addAttribute("cancelUrl", WebUtils.createUrl("/collectedNews/categories/" + category.getAlias() + ".html"));
            return "confirm/delete";
        } else {
            if (idset.size() == 1) {
                CollectedNews news = collectedNewsService.find(Integer.valueOf(ids[0]).intValue());
                if (null != news) {
                    collectedNewsService.delete(news);
                    WebUtils.addSuccessFlashMessage(String.format("成功删除：<i>%s</i>", news.getTitle()));
                } else {
                    WebUtils.addErrorFlashMessage("记录不存在或已被删除");
                }
            } else {
                int count = collectedNewsService.delete(idset);
                WebUtils.addSuccessFlashMessage(String.format("成功删除 <i>%d</i> 条记录", count));
            }
            
            return WebUtils.redirectUrl("/collectedNews/categories/"+category.getAlias()+".html");
        }
    }
}