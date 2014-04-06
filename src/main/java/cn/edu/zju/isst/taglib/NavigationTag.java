package cn.edu.zju.isst.taglib;

import java.util.Map;

import javax.servlet.jsp.tagext.TagSupport;

public class NavigationTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String active;
    private Map<String, NavigationLink> menu;
    
    public void setActive(String active) {
        this.active = active;
    }
    
    public void setMenu(Map<String, NavigationLink> menu) {
        this.menu = menu;
    }
    
    public int doStartTag() {
        return EVAL_BODY_INCLUDE;
    }
    
    public NavigationLink getMenu(String key) {
        return menu.get(key);
    }
    
    public String getActive() {
        return active;
    }
}