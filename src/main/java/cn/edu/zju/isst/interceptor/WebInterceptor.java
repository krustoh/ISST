package cn.edu.zju.isst.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zju.isst.common.WebUtils;
import cn.edu.zju.isst.dao.StudentUserDao;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.identity.UserIdentity;

public class WebInterceptor extends AbstractMvcInterceptor {
    @Autowired
    private StudentUserDao studentUserDao;
    
    public boolean onPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isAccessible = true;
        RequireUser ru = getAnnotation(handler, RequireUser.class);
        StudentUser user = UserIdentity.read(request, studentUserDao);
        
        if (null != ru) {
            if (user.getId() == 0) {
                isAccessible = false;
            }
        }
        
        if (isAccessible) {
            return true;
        }
        
        String loginUrl = WebUtils.createWebUrl("login.html") + "?returnUrl=" + URLEncoder.encode(WebUtils.requestUrl(), "utf-8");
        response.sendRedirect(loginUrl);
        
        return false;
    }
}