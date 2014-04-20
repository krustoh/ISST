package cn.edu.zju.isst.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.edu.zju.isst.common.WebUtils;

public class ReturnUrlTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String url;
    
    public int doEndTag() throws JspException {
        if (null != url && !url.isEmpty()) {
            url = WebUtils.createUrl(url);
        }
        
        String returnUrl = this.pageContext.getRequest().getParameter(WebUtils.RETURN_URL_KEY);
        if (null == returnUrl || returnUrl.isEmpty()) {
            returnUrl = url;
        }
        
        try {
            this.pageContext.getOut().append(returnUrl);
        } catch (IOException e) {
            throw new JspException(e);
        }
        
        return EVAL_PAGE;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}