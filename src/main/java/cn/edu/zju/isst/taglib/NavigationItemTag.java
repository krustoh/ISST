package cn.edu.zju.isst.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;

import org.apache.taglibs.standard.tag.rt.core.IfTag;

public class NavigationItemTag extends BodyTagSupport implements DynamicAttributes {
    private static final long serialVersionUID = 1L;
    
    private String tag = "li";
    private String activeCssClass = "active";
    private Map<String, Object> attributes;
    
    private boolean active;
    private NavigationLink navigationLink;
    
    private boolean hidden = false;
    
    public int doEndTag() throws JspException {
        
        if (active) {
            Navigation.addFirstBreadcrumb(pageContext, navigationLink);
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
        }

        Tag parent = this.getParent();
        while (null != parent && parent instanceof IfTag) {
            parent = parent.getParent();
        }
        
        if (null != parent && parent instanceof NavigationItemTag) {
            NavigationItemTag parentItem = (NavigationItemTag) parent;
            if (active) {
                parentItem.setActive(true);
            }
            if (parentItem.isHidden()) {
                hidden = true;
            }
        }
        
        if (!hidden) {
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
                throw new JspException(e);
            }
        }
        
        attributes = null;
        navigationLink = null;
        active = false;
        hidden = false;
        
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

    public void setActiveCssClass(String activeCssClass) {
        this.activeCssClass = activeCssClass;
    }

    public void setNavigationLink(NavigationLink link) {
        this.navigationLink = link;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }
}