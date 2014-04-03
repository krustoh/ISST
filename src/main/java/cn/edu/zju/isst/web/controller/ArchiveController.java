package cn.edu.zju.isst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.service.ArchiveService;

@RequireUser
@Controller
public class ArchiveController {
	@Autowired
	private ArchiveService archiveService;
	
	@RequestMapping(value = "/archives/list.html", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "keywords", required = false, defaultValue = "") String keywords, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		model.addAttribute("archives", archiveService.findAll("campus", keywords, 20, page));
        return "archives/list";
    }
	
	@RequestMapping(value = "/archives/content.html", method = RequestMethod.GET)
    public String content(Model model) {
        return "archives/content";
    }
}