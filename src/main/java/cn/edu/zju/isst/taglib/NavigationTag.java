package cn.edu.zju.isst.taglib;

import java.util.LinkedList;
import java.util.Map;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class NavigationTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String breadcrumbKey;
    private String active;
    private Map<String, NavigationLink> menu;
    private transient LinkedList<NavigationLink> breadcrumbs;
    public void setActive(String active) {
        this.active = active;
    }
    
    public void setMenu(Map<String, NavigationLink> menu) {
        this.menu = menu;
    }
    
    public void setBreadcrumbs(String breadcrumbKey) {
        this.breadcrumbKey = breadcrumbKey;
    }
    
    public int doStartTag() {
        return EVAL_BODY_INCLUDE;
    }
    
    public void addBreadcrumb(NavigationLink link) {
        if (null != breadcrumbKey && null != link) {
            if (null == breadcrumbs) {
                breadcrumbs = new LinkedList<NavigationLink>();
            }
            breadcrumbs.addFirst(link);
        }
    }
    
    public void addBreadcrumb(String key) {
        addBreadcrumb(getMenu(key));
    }
    
    public NavigationLink getMenu(String key) {
        return menu.get(key);
    }
    
    public String getActive() {
        return active;
    }
    
    public int doEndTag() {
        if (null != breadcrumbKey && null != breadcrumbs) {
            this.pageContext.setAttribute(breadcrumbKey, breadcrumbs, PageContext.REQUEST_SCOPE);
        }
        
        breadcrumbKey = null;
        breadcrumbs = null;
        
        return EVAL_PAGE;
    }
}