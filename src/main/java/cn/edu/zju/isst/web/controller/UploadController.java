package cn.edu.zju.isst.web.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import cn.edu.zju.isst.common.AppConfig;

@Controller
public class UploadController {
    
    @RequestMapping(value = "/uploadfiles/**", method = RequestMethod.GET)
    public @ResponseBody FileSystemResource getFile(HttpServletRequest request) {
        String pathInfo = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String fileName = null;
        if (null != pathInfo) {
            fileName = pathInfo.substring(12);
        }
        File file = new File(AppConfig.UPLOAD_BASE_PATH + fileName);
        if (!file.exists()) {
            return null;
        }
        return new FileSystemResource(file); 
    }
}