package cn.edu.zju.isst.common;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    public static String createWebUrl(HttpServletRequest request, String path) {
        return request.getContextPath() + "/web/" + path;
    }
    
    public static String redirectWebUrl(HttpServletRequest request, String path) {
        return "redirect:" + createWebUrl(request, path);
    }
    
    public static String createAdminUrl(HttpServletRequest request, String path) {
        return request.getContextPath() + "/admin/" + path;
    }
    
    public static String createResourceUrl(HttpServletRequest request, String path) {
        return request.getContextPath()+"/resources/admin/" + path;
    }
    
    public static String redirectAdminUrl(HttpServletRequest request, String path) {
        return "redirect:" + createAdminUrl(request, path);
    }
}