package cn.edu.zju.isst.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.dao.AdministratorDao;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.identity.AdministratorIdentity;
import cn.edu.zju.isst.identity.RequireAdministrator;

public class AdministratorInterceptor extends AuthenticationInterceptor {
    @Autowired
    private AdministratorDao administratorDao;
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isAccessible = true;
        RequireAdministrator ra = getAnnotation(handler, RequireAdministrator.class);
        Administrator administrator = AdministratorIdentity.read(request, administratorDao);
        
        request.setAttribute("response", response);
        
        if (null != ra) {
            for (int role : ra.value()) {
                if (!administrator.hasRole(role)) {
                    isAccessible = false;
                    break;
                }
            }
        }
        
        if (isAccessible) {
            return true;
        }
        
        String loginUrl = WebUtils.createAdminUrl("login.html") + "?returnUrl=" + URLEncoder.encode(WebUtils.requestUrl(), "utf-8");
        response.sendRedirect(loginUrl);
        
        return false;
    }

    public void postHandle(
            HttpServletRequest request, 
            HttpServletResponse response, 
            Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView.getView() instanceof RedirectView || modelAndView.getViewName().startsWith("redirect:")) {  
        } else {
            modelAndView.addObject("resourceUrl", WebUtils.createResourceUrl(""));
            modelAndView.addObject("baseUrl", WebUtils.createAdminUrl(""));
            modelAndView.addObject("requestUrl", WebUtils.requestUrl());
        }
    }
}