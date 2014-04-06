package cn.edu.zju.isst.common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;

public class WebUtils {
    public static final String ADMIN_BASE_PATH = "/admin/";
    public static final String WEB_BASE_PATH = "/web/";
    public static final String FLASH_MESSAGE_KEY = "flash_message";
    
    public static String createWebUrl(HttpServletRequest request, String path) {
        return request.getContextPath() + "/web/" + path;
    }
    
    public static String redirectWebUrl(HttpServletRequest request, HttpServletResponse response, String path) {
        return redirectUrl(request, response, path, WEB_BASE_PATH);
    }
    
    public static String createAdminUrl(HttpServletRequest request, String path) {
        return request.getContextPath() + "/admin/" + path;
    }
    
    public static String createResourceUrl(HttpServletRequest request, String path) {
        return request.getContextPath()+"/resources/admin/" + path;
    }
    
    public static String redirectAdminUrl(HttpServletRequest request, HttpServletResponse response, String path) {
        return redirectUrl(request, response, path, ADMIN_BASE_PATH);
    }
    
    public static void addErrorFlashMessage(HttpSession session, String message) {
        session.setAttribute(FLASH_MESSAGE_KEY, FlashMessage.error(message));
    }
    
    public static void addErrorFlashMessage(HttpSession session, BindingResult result) {
        session.setAttribute(FLASH_MESSAGE_KEY, FlashMessage.error(result));
    }
    
    public static void addSuccessFlashMessage(HttpSession session, String message) {
        session.setAttribute(FLASH_MESSAGE_KEY, FlashMessage.success(message));
    }
    
    private static String redirectUrl(HttpServletRequest request, HttpServletResponse response, String path, String prefix) {
        String returnUrl = request.getParameter("returnUrl");
        if (null != returnUrl) {
            path = returnUrl;
        }
        
        if (null != path && (path.startsWith("http://") || path.startsWith("https://"))) {
            try {
                response.sendRedirect(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return "redirect:"+prefix + path;
        }
    }
}