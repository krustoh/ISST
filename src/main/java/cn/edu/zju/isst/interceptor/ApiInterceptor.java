package cn.edu.zju.isst.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.zju.isst.common.ApiResponse;
import cn.edu.zju.isst.dao.StudentUserDao;
import cn.edu.zju.isst.entity.StudentUser;
import cn.edu.zju.isst.identity.RequireUser;
import cn.edu.zju.isst.identity.UserIdentity;

public class ApiInterceptor extends AuthenticationInterceptor {
    @Autowired
    private StudentUserDao studentUserDao;
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(new JSONObject(new ApiResponse(1, "未登录")));
        
        return false;
    }
}
