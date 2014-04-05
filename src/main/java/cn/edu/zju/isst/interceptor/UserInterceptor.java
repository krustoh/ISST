package cn.edu.zju.isst.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zju.isst.dao.UserDao;
import cn.edu.zju.isst.entity.User;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.identity.UserIdentity;

public class UserInterceptor extends AuthenticationInterceptor {
    @Autowired
    private UserDao userDao;
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isAccessible = true;
        RequireUser ru = getAnnotation(handler, RequireUser.class);
        User user = UserIdentity.read(request, userDao);
        
        if (null != ru) {
            if (user.getId() == 0) {
                isAccessible = false;
            }
        }
        
        if (isAccessible) {
            return true;
        }
        
        response.sendRedirect(request.getContextPath() + "/login.html");
        
        return false;
    }
}
