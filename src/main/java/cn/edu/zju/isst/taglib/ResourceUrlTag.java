package cn.edu.zju.isst.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import cn.edu.zju.isst.common.WebUtils;

public class ResourceUrlTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    
    private String path;
    
    public int doEndTag() throws JspException {
        try {
            this.pageContext.getOut().append(WebUtils.createResourceUrl(path));
        } catch (IOException e) {
            throw new JspException(e);
        }
        
        return EVAL_PAGE;
    }

    public void setPath(String path) {
        this.path = path;
    }
}