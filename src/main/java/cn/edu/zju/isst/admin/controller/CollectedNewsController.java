package cn.edu.zju.isst.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.zju.isst.service.CollectedNewsService;

@Controller
public class CollectedNewsController {
    @Autowired
    private CollectedNewsService collectedNewsService;
    
    @RequestMapping("/news/collection/collect")
    public String collect() {
        collectedNewsService.collectAll();
        return "redirect:index.html";
    }
    
    @RequestMapping("/news/collection/publish")
    public String publish() {
        collectedNewsService.publishAll();
        return "redirect:index.html";
    }
}
