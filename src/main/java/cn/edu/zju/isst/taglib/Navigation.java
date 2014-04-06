package cn.edu.zju.isst.taglib;

import java.util.LinkedList;

import javax.servlet.jsp.PageContext;

public class Navigation {
    public static final String BREADCRUMBS_ATTRIBUTE_KEY = "breadcrumbs";
    public static final String PAGE_TITLE_ATTRIBUTE_KEY = "pageTitle";
    
    public static void setPageTitle(PageContext pageContext, String pageTitle) {
        pageContext.setAttribute(PAGE_TITLE_ATTRIBUTE_KEY, pageTitle, PageContext.REQUEST_SCOPE);
    }
    
    public static String getPageTitle(PageContext pageContext) {
        return (String) pageContext.getAttribute(PAGE_TITLE_ATTRIBUTE_KEY, PageContext.REQUEST_SCOPE);
    }
    
    public static void addBreadcrumb(PageContext pageContext, NavigationLink link) {
        if (null != link) {
            getBreadcrumbs(pageContext).add(link);
        }
    }

    public static void addFirstBreadcrumb(PageContext pageContext, NavigationLink link) {
        if (null != link) {
            getBreadcrumbs(pageContext).addFirst(link);
        }
    }
    
    public static void addLastBreadcrumb(PageContext pageContext, NavigationLink link) {
        if (null != link) {
            getBreadcrumbs(pageContext).addLast(link);
        }
    }
    
    public static LinkedList<NavigationLink> getBreadcrumbs(PageContext pageContext) {
        @SuppressWarnings("unchecked")
        LinkedList<NavigationLink> breadcrumbs = (LinkedList<NavigationLink>) pageContext.getAttribute(BREADCRUMBS_ATTRIBUTE_KEY, PageContext.REQUEST_SCOPE);
        if (null == breadcrumbs) {
            breadcrumbs = new LinkedList<NavigationLink>();
            pageContext.setAttribute(BREADCRUMBS_ATTRIBUTE_KEY, breadcrumbs, PageContext.REQUEST_SCOPE);
        }
        
        return breadcrumbs;
    }
}