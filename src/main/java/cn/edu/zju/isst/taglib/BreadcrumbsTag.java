package cn.edu.zju.isst.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class BreadcrumbsTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    
    private String cssClass = "breadcrumb";
    
    public int doEndTag() {
        JspWriter out = this.pageContext.getOut();
        try {
            out.append(String.format("<ul class=\"%s\">", cssClass));
            int i = 0;
            for (NavigationLink link : Navigation.getBreadcrumbs(pageContext)) {
                out.append("<li>");
                if (i == 0) {
                    out.append("<i class=\"icon-home home-icon\"></i> ");
                }
                out.append(String.format("<a href=\"%s\">%s</a>", link.getUrl(), link.getLabel()));
                out.append("</li>");
                i++;
            }
            out.append("</ul>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return EVAL_PAGE;
    }
}