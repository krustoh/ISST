package cn.edu.zju.isst.taglib;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.edu.zju.isst.common.WebUtils;

public class CreateUrlTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    
    private String url;
    private boolean returned;
    
    public int doEndTag() throws JspException {
        try {
            String newUrl = WebUtils.createUrl(url);
            if (returned) {
                StringBuilder sb = new StringBuilder(newUrl);
                sb.append(newUrl.contains("?") ? "&" : "?")
                    .append(WebUtils.RETURN_URL_KEY)
                    .append("=")
                    .append(URLEncoder.encode(WebUtils.requestUrl(), "utf-8"));
                newUrl = sb.toString();
            }
            
            this.pageContext.getOut().append(newUrl);
        } catch (IOException e) {
            throw new JspException(e);
        }
        
        return EVAL_PAGE;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setReturned(boolean returned) {
        this.returned = returned;
    }
}