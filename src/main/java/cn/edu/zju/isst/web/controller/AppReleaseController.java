package cn.edu.zju.isst.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.entity.AppRelease;
import cn.edu.zju.isst.service.AppReleaseService;

@Controller("webAppReleaseController")
public class AppReleaseController {
	@Autowired
	private AppReleaseService appReleaseService;

	@RequestMapping("/isst-release")
	public String getAndroidLatestVersion() {
		AppRelease ar = appReleaseService.getLatestVersion();
		if (ar != null)
			return WebUtils.redirectUrl(ar.getUrl());
		return WebUtils.redirectUrl("/index.html");
	}
}
