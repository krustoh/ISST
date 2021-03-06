package cn.edu.zju.isst.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

public class WebUtils {
    public static final String RETURN_URL_KEY = "returnUrl";
    
    public static String createUrl(String path) {
        if (null != path && (path.equals("#") || path.startsWith("http://") || path.startsWith("https://"))) {
            return path;
        } else if (null == path) {
            return baseUrl();
        } else if (!path.startsWith("/")) {
            path = "/" + path;
        }
        
        return baseUrl() + path;
    }
    
    public static String createWebUrl(String path) {
        return contextUrl() + AppConfig.WEB_BASE_PATH + path;
    }
    
    public static String redirectWebUrl(String path) {
        return redirectUrl(createWebUrl(path));
    }
    
    public static String createAdminUrl(String path) {
        return contextUrl() + AppConfig.ADMIN_BASE_PATH + path;
    }
    
    public static String createResourceUrl(String path) {
        return baseUrl() + AppConfig.RESOURCE_BASE_PATH + path;
    }
    
    public static String redirectAdminUrl(String path) {
        return redirectUrl(createAdminUrl(path));
    }
    
    public static void addErrorFlashMessage(String message) {
        request().getSession().setAttribute(AppConfig.FLASH_MESSAGE_KEY, FlashMessage.error(message));
    }
    
    public static void addErrorFlashMessage(Result result) {
        request().getSession().setAttribute(AppConfig.FLASH_MESSAGE_KEY, FlashMessage.error(result));
    }
    
    public static void addErrorFlashMessage(BindingResult result) {
        request().getSession().setAttribute(AppConfig.FLASH_MESSAGE_KEY, FlashMessage.error(result));
    }
    
    public static void addSuccessFlashMessage(String message) {
        request().getSession().setAttribute(AppConfig.FLASH_MESSAGE_KEY, FlashMessage.success(message));
    }
    
    public static String saveUploadedFile(MultipartFile file) {
        if (null != file && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String extensionName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String fileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;

            SimpleDateFormat dateformat = new SimpleDateFormat("/yyyy/MM/");    
            String path = dateformat.format(new Date());  
            String realPath = AppConfig.UPLOAD_BASE_PATH + path;
            File realPathFile = new File(realPath);  
            if(!realPathFile.exists()) {
                realPathFile.mkdirs();
            }
            
            File savedFile = new File(realPath + fileName);
            try {
                file.transferTo(savedFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            return path + fileName;
        }
        
        return null;
    }
    
    public static void deleteUploadedFile(String path) {
        if (null != path && path.startsWith("/")) {
            String realPath = AppConfig.UPLOAD_BASE_PATH + path;
            File realPathFile = new File(realPath);
            if(realPathFile.exists()) {
                realPathFile.delete();
            }
        }
    }
    
    public static String parseUploadedUrl(String path) {
        if (null != path && path.startsWith("/")) {
            return contextUrl() + AppConfig.UPLOAD_BASE_URI + path;
        }
        return path;
    }
    
    public static HttpServletRequest request() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    public static HttpServletResponse response() {
        return (HttpServletResponse) request().getAttribute("response");
    }
    
    public static String contextUrl() {
        HttpServletRequest request = request();
        String contextUrl = (String) request.getAttribute("_contextUrl");
        if (null == contextUrl) {
            int port = request.getServerPort();
            contextUrl = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : (":" + port)) + request.getContextPath(); 
            request.setAttribute("_contextUrl", contextUrl);
        }
        return contextUrl;
    }
    
    public static String baseUrl() {
        HttpServletRequest request = request();
        String baseUrl = (String) request.getAttribute("_baseUrl");
        if (null == baseUrl) {
            String servletPath = request.getServletPath();
            if ("/admin".equals(servletPath)) {
                baseUrl = contextUrl() + servletPath;
            } else {
                baseUrl = contextUrl();
            }
            request.setAttribute("_baseUrl", baseUrl);
        }
        return baseUrl;
    }
    
    public static String requestUrl() {
        HttpServletRequest request = request();
        if (null == request.getAttribute("_requestUrl")) {
            String baseUrl = request.getRequestURL().toString();
            String queryString = request.getQueryString();
            request.setAttribute("_requestUrl", (null == queryString || queryString.length() == 0) ? baseUrl : (baseUrl+"?"+queryString));
        }
        
        return (String) request.getAttribute("_requestUrl");
    }
    
    public static String redirectUrl(String path) {
        String returnUrl = request().getParameter(RETURN_URL_KEY);
        if (null != returnUrl) {
            path = returnUrl;
        } else {
            path = createUrl(path);
        }
        
        return "redirect:" + path;
    }
}