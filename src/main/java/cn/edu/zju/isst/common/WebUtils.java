package cn.edu.zju.isst.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.multipart.MultipartFile;

public class WebUtils {
    public static final String ADMIN_BASE_PATH = "/admin/";
    public static final String WEB_BASE_PATH = "/web/";
    public static final String FLASH_MESSAGE_KEY = "flash_message";
    public static final String UPLOAD_PATH = "/uploads";
    
    public static String createWebUrl(String path) {
        return request().getContextPath() + "/web/" + path;
    }
    
    public static String redirectWebUrl(String path) {
        return redirectUrl(path, WEB_BASE_PATH);
    }
    
    public static String createAdminUrl(String path) {
        return request().getContextPath() + "/admin/" + path;
    }
    
    public static String createResourceUrl(String path) {
        return request().getContextPath()+"/resources/admin/" + path;
    }
    
    public static String redirectAdminUrl(String path) {
        return redirectUrl(path, ADMIN_BASE_PATH);
    }
    
    public static void addErrorFlashMessage(String message) {
        request().getSession().setAttribute(FLASH_MESSAGE_KEY, FlashMessage.error(message));
    }
    
    public static void addErrorFlashMessage(BindingResult result) {
        request().getSession().setAttribute(FLASH_MESSAGE_KEY, FlashMessage.error(result));
    }
    
    public static void addSuccessFlashMessage(String message) {
        request().getSession().setAttribute(FLASH_MESSAGE_KEY, FlashMessage.success(message));
    }
    
    public static String saveUploadedFile(MultipartFile file) {
        if (null != file && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String extensionName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            String fileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;

            SimpleDateFormat dateformat = new SimpleDateFormat("/yyyy/MM/");    
            String path = dateformat.format(new Date());  
            String realPath = request().getSession().getServletContext().getRealPath("/") + UPLOAD_PATH + path;
            File realPathFile = new File(realPath);  
            if(!realPathFile.exists()) {
                realPathFile.mkdirs();
            }
            
            try {
                FileOutputStream out = new FileOutputStream(realPath + fileName);
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            return path + "/" + fileName;
        }
        
        return null;
    }
    
    public static void deleteUploadedFile(String path) {
        String realPath = request().getSession().getServletContext().getRealPath("/") + UPLOAD_PATH + path;
        File realPathFile = new File(realPath);  
        if(realPathFile.exists()) {
            realPathFile.delete();
        }
    }
    
    public static String parseUploadedUrl(String path) {
        if (null != path && path.startsWith("/")) {
            return request().getContextPath() + UPLOAD_PATH + path;
        }
        return path;
    }
    
    public static HttpServletRequest request() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
    
    public static HttpServletResponse response() {
        return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
    }
    
    private static String redirectUrl(String path, String prefix) {
        String returnUrl = request().getParameter("returnUrl");
        if (null != returnUrl) {
            path = returnUrl;
        }
        
        if (null != path && (path.startsWith("http://") || path.startsWith("https://"))) {
            try {
                response().sendRedirect(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return "redirect:"+prefix + path;
        }
    }
}