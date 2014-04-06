package cn.edu.zju.isst.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;

public class NavigationItemTag extends BodyTagSupport implements DynamicAttributes {
    private static final long serialVersionUID = 1L;
    
    private String tag = "li";
    private String key;
    private Map<String, Object> attributes;
    private boolean active;
    private String activeCssClass = "active";
    
    public int doStartTag() {
        return EVAL_BODY_BUFFERED;
    }
    
    public int doEndTag() {
        NavigationTag navigationTag = (NavigationTag) findAncestorWithClass(this, NavigationTag.class);
        
        if (!active) {
            if (null != navigationTag) {
                String activeKey = navigationTag.getActive();
                if (null != activeKey && activeKey.equals(key)) {
                    active = true;
                    Tag parent = this.getParent();
                    if (null != parent && parent instanceof NavigationItemTag) {
                        ((NavigationItemTag)parent).setActive(true);
                    }
                }
            }
            
        }
        
        if (active) {
            if (null == attributes) {
                attributes = new HashMap<String, Object>();
                attributes.put("class", activeCssClass);
            } else {
                if (attributes.containsKey("class")) {
                    attributes.put("class", attributes.get("class") + " " + activeCssClass);
                } else {
                    attributes.put("class", activeCssClass);
                }
            }
            
            if (null != navigationTag) {
                navigationTag.addBreadcrumb(navigationTag.getMenu(key));
            }
        }
        
        StringBuilder sb = new StringBuilder();
        if (null != attributes) {
            for (String key : attributes.keySet()) {
                sb.append(" ").append(key).append("=\"").append(attributes.get(key)).append("\"");
            }
        }
        
        JspWriter out = pageContext.getOut();
        try {
            out.append(String.format("<%s%s>%s</%s>", tag, sb.toString(), null == this.bodyContent ? "" : this.bodyContent.getString(), tag));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        attributes = null;
        active = false;
        
        return EVAL_PAGE;
    }
    
    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (null == attributes) {
            attributes = new HashMap<String, Object>();
        }
        attributes.put(localName.toLowerCase(), value);
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setActiveCssClass(String activeCssClass) {
        this.activeCssClass = activeCssClass;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}