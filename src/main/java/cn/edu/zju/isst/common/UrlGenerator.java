package cn.edu.zju.isst.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class UrlGenerator {
    private String baseUrl;
    private Map<String, String> parameters = new HashMap<String, String>();
    
    @SuppressWarnings("unchecked")
    public UrlGenerator(HttpServletRequest request) {
        baseUrl = (String) request.getAttribute("javax.servlet.forward.request_uri");
        Map<String, String[]> rawData = (Map<String, String[]>)request.getParameterMap();
        for (Map.Entry<String, String[]> entry : rawData.entrySet()) {
            parameters.put(entry.getKey(), entry.getValue()[0]);
        }
    }
    
    public String setParameter(String key, String value) {
        String oldValue = parameters.get(key);
        parameters.put(key, value);
        
        return oldValue;
    }
    
    public String getParameter(String key) {
        return parameters.get(key);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder(baseUrl);
        
        if (!parameters.isEmpty()) {
            sb.append("?");
        }
        
        int i = 0;
        for (String key : parameters.keySet()) {
            if (i > 0) {
                sb.append("&");
            }
            String value = parameters.get(key);
            sb.append(key);
            sb.append("=");
            try {
                sb.append(value == null ? "" : URLEncoder.encode(value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }
        
        return sb.toString();
    }
}