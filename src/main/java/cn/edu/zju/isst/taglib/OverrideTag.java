package cn.edu.zju.isst.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class OverrideTag extends BodyTagSupport {
    private static final long serialVersionUID = 8379959647039117369L;
    
    private String name;
    private String attributeName;
    private String attributeTypeName;
    private boolean override = false;
    private boolean prevOverride = false;
    private boolean append = true;
    
    @Override
    public int doStartTag() throws JspException {
        attributeName = BlockTag.getAttributeName(name);
        attributeTypeName = BlockTag.getAttributeOverrideTypeName(name);
        Boolean isPrevOverride = (Boolean) pageContext.getRequest().getAttribute(attributeTypeName);
        prevOverride = null == isPrevOverride ? false : isPrevOverride.booleanValue();
        if (prevOverride) {
            return SKIP_BODY;
        } else {
            return EVAL_BODY_BUFFERED;
        }
    }
    
    @Override
    public int doEndTag() throws JspException {
        if(!prevOverride) {
            String content = (String) pageContext.getRequest().getAttribute(attributeName);
            
            BodyContent bodyContent = getBodyContent();
            if (null != bodyContent) {
                content = null == content ? bodyContent.getString() : (append ? (bodyContent.getString() + content) : (content + bodyContent.getString()));
            }
            
            pageContext.getRequest().setAttribute(attributeName, content);
            pageContext.getRequest().setAttribute(attributeTypeName, override);
            pageContext.getRequest().setAttribute(BlockTag.getAttributeAppendTypeName(name), append);
        }
        
        return EVAL_PAGE;
    }
    
    public void setOverride(boolean override) {
        this.override = override;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}