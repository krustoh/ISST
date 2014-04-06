package cn.edu.zju.isst.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class BlockTag extends BodyTagSupport {
    private static final long serialVersionUID = 8246166191638588615L;
    
    public static String BLOCK = "__jsp_override__";
    
    private String name;
    private boolean prevOverride = false;
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public int doStartTag() throws JspException {
        Boolean isPrevOverride = (Boolean) pageContext.getRequest().getAttribute(getAttributeOverrideTypeName(name));
        prevOverride = null == isPrevOverride ? false : isPrevOverride.booleanValue();
        
        if (prevOverride) {
            return SKIP_BODY;
        }
        
        return EVAL_BODY_BUFFERED ;
    }
    
    @Override
    public int doEndTag() throws JspException {
        String content = (String) pageContext.getRequest().getAttribute(getAttributeName(name));
        
        if (!prevOverride) {
            Boolean isAppend = (Boolean) pageContext.getRequest().getAttribute(getAttributeAppendTypeName(name));
            boolean append = null == isAppend ? false : isAppend.booleanValue();
            
            BodyContent bodyContent = getBodyContent();
            if (null != bodyContent) {
                content = null == content ? bodyContent.getString() : (append ? (bodyContent.getString() + content) : (content + bodyContent.getString()));
            }
        }
        
        if (null != content) {
            try {
                pageContext.getOut().write(content);
            } catch (IOException e) {
                throw new JspException("write overridedContent occer IOException,block name:"+name,e);
            }
        }
        
        return EVAL_PAGE;
    }

    public static String getAttributeName(String name) {
        return BLOCK + name;
    }
    
    public static String getAttributeOverrideTypeName(String name) {
        return BLOCK + name + "_type";
    }
    
    public static String getAttributeAppendTypeName(String name) {
        return BLOCK + name + "_append";
    }
}
