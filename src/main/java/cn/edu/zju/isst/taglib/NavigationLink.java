package cn.edu.zju.isst.taglib;

import java.io.Serializable;

public class NavigationLink implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String label;
    private String url;
    
    public NavigationLink() {
    }
    
    public NavigationLink(String label, String url) {
        this.label = label;
        this.url = url;
    }
    
    public String getLabel() {
        return label;
    }
    
    public String getUrl() {
        return url;
    }
}