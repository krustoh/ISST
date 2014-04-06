package cn.edu.zju.isst.taglib;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.jsp.PageContext;

public class Navigation {
    public static final String BREADCRUMBS_ATTRIBUTE_KEY = "breadcrumbs";
    public static final String PAGE_TITLE_ATTRIBUTE_KEY = "pageTitle";
    public static final String NAVIGATION_ACTIVE_ATTRIBUTE_KEY = "navigationActiveKey";
    public static final String NAVIGATION_MENU_ATTRIBUTE_KEY = "navigationMenu";
    
    public static void addMenu(PageContext pageContext, String key, NavigationLink link) {
        getMenuMap(pageContext).put(key, link);
    }
    
    public static NavigationLink getMenu(PageContext pageContext, String key) {
        return getMenuMap(pageContext).get(key);
    }
    
    public static Map<String, NavigationLink> getMenuMap(PageContext pageContext) {
        @SuppressWarnings("unchecked")
        Map<String, NavigationLink> map = (Map<String, NavigationLink>) pageContext.getAttribute(NAVIGATION_MENU_ATTRIBUTE_KEY, PageContext.REQUEST_SCOPE);
        if (null == map) {
            map = new HashMap<String, NavigationLink>();
            pageContext.setAttribute(NAVIGATION_MENU_ATTRIBUTE_KEY, map, PageContext.REQUEST_SCOPE);
        }
        
        return map;
    }
    
    public static void setPageTitle(PageContext pageContext, String pageTitle) {
        pageContext.setAttribute(PAGE_TITLE_ATTRIBUTE_KEY, pageTitle, PageContext.REQUEST_SCOPE);
    }
    
    public static void setNavigationActiveKey(PageContext pageContext, String key) {
        pageContext.setAttribute(NAVIGATION_ACTIVE_ATTRIBUTE_KEY, key, PageContext.REQUEST_SCOPE);
    }
    
    public static String getNavigationActiveKey(PageContext pageContext) {
        return (String) pageContext.getAttribute(NAVIGATION_ACTIVE_ATTRIBUTE_KEY, PageContext.REQUEST_SCOPE);
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