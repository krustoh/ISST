package cn.edu.zju.isst.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.dao.AdministratorDao;
import cn.edu.zju.isst.entity.Administrator;
import cn.edu.zju.isst.identity.AdministratorIdentity;
import cn.edu.zju.isst.identity.RequireAdministrator;

public class AdminInterceptor extends AbstractMvcInterceptor {
    @Autowired
    private AdministratorDao administratorDao;
    
    @Override
    public boolean onPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isAccessible = true;
        RequireAdministrator ra = getAnnotation(handler, RequireAdministrator.class);
        Administrator administrator = AdministratorIdentity.read(request, administratorDao);
        
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
        
        String loginUrl = WebUtils.createUrl("/login.html") + "?returnUrl=" + URLEncoder.encode(WebUtils.requestUrl(), "utf-8");
        response.sendRedirect(loginUrl);
        
        return false;
    }
}