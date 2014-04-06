package cn.edu.zju.isst.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

public class NavigationLinkTag extends BodyTagSupport implements DynamicAttributes {
    private static final long serialVersionUID = 1L;
    
    private String key;
    private String href;
    private String label;
    private Map<String, Object> attributes;
    
    @Override
    public int doStartTag() {
        return EVAL_BODY_BUFFERED;
    }
    
    @Override
    public int doEndTag() {
        NavigationTag navigationTag = (NavigationTag) findAncestorWithClass(this, NavigationTag.class);
        
        if (null != navigationTag) {
            NavigationLink link = navigationTag.getMenu(key);
            if (null != link) {
                if (null == label) {
                    label = link.getLabel();
                }
                if (null == href) {
                    href = link.getUrl();
                }
            }
        }
        
        attributes.put("href", href);
        
        String content = null == this.bodyContent ? null : this.bodyContent.getString();
        if (null == label) {
            label = content;
        } else if (content == null || content.isEmpty()) {
            content = label;
        }
        
        StringBuilder sb = new StringBuilder();
        for (String key : attributes.keySet()) {
            sb.append(" ").append(key).append("=\"").append(attributes.get(key)).append("\"");
        }
        
        JspWriter out = pageContext.getOut();
        try {
            out.append(String.format("<%s%s>%s</%s>", "a", sb.toString(), content, "a"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        attributes = null;
        label = null;
        href = null;
        
        return EVAL_PAGE;
    }
    
    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (null == attributes) {
            attributes = new HashMap<String, Object>();
        }
        attributes.put(localName.toLowerCase(), value);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}