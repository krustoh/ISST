package cn.edu.zju.isst.identity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.dao.UserDao;
import cn.edu.zju.isst.entity.User;

public class ApiAuthenticationInterceptor extends AuthenticationInterceptor {
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
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(new JSONObject(new ApiResponse(1, "未登录")));
        
        return false;
    }
}
