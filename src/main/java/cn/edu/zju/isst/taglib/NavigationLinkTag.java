package cn.edu.zju.isst.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.Tag;

import cn.edu.zju.isst.common.WebUtils;

public class NavigationLinkTag extends BodyTagSupport implements DynamicAttributes {
    private static final long serialVersionUID = 1L;
    
    private String key;
    private String href;
    private String label;
    private Map<String, Object> attributes;
    
    @Override
    public int doEndTag() throws JspException {
        setDynamicAttribute(null, "href", href);
        String content = null == this.bodyContent ? null : this.bodyContent.getString();
        if (null == label) {
            label = content != null ? content.trim() : "";
        } else if (content == null || content.isEmpty()) {
            content = label;
        }
        
        NavigationLink link = new NavigationLink(label, href);
        Navigation.addMenu(pageContext, key, link);
        Tag parentTag = this.getParent();
        NavigationItemTag parent = null;
        if (null != parentTag && parentTag instanceof NavigationItemTag) {
            parent = (NavigationItemTag) parentTag;
            parent.setNavigationLink(link);
        }
        
        String activeKey = Navigation.getNavigationActiveKey(pageContext);
        if (null != activeKey && activeKey.equals(key)) {
            if (null != parent) {
                parent.setActive(true);
            }
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
        this.href = WebUtils.createUrl(href);
    }

    public void setLabel(String label) {
        this.label = label;
    }
}