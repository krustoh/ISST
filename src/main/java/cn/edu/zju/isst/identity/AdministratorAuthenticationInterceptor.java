package cn.edu.zju.isst.identity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zju.isst.dao.AdministratorDao;
import cn.edu.zju.isst.entity.Administrator;

public class AdministratorAuthenticationInterceptor extends AuthenticationInterceptor {
    @Autowired
    private AdministratorDao administratorDao;
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
        
        response.sendRedirect(request.getContextPath() + "/admin/login.html");
        
        return false;
    }
}
