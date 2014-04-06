package cn.edu.zju.isst.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

public class NavigationItemTag extends BodyTagSupport implements DynamicAttributes {
    private static final long serialVersionUID = 1L;
    
    private String tag = "li";
    private String activeCssClass = "active";
    private Map<String, Object> attributes;
    
    private NavigationLink activeNavigationLink;
    
    public int doEndTag() {
        if (null != activeNavigationLink) {
            Navigation.addFirstBreadcrumb(pageContext, activeNavigationLink);
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
        activeNavigationLink = null;
        
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

    public void setActiveNavigationLink(NavigationLink link) {
        this.activeNavigationLink = link;
    }
}