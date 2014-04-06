package cn.edu.zju.isst.taglib;

import javax.servlet.jsp.tagext.TagSupport;

public class AddNavigationMenuTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    
    private String key;
    private String label;
    private String url;
    
    public int doEndTag() {
        Navigation.addMenu(pageContext, key, new NavigationLink(label, url));
        return EVAL_PAGE;
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
}