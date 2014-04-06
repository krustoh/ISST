package cn.edu.zju.isst.taglib;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageSubTitleTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    
    public int doEndTag() {
        JspWriter out = this.pageContext.getOut();
        try {
            String pageTitle = Navigation.getPageTitle(pageContext);
            LinkedList<NavigationLink> list = Navigation.getBreadcrumbs(pageContext);
            if (null == pageTitle) {
                if (list.size() >= 2) {
                    out.append(String.format(
                        "<h1>%s <small><i class=\"icon-double-angle-right\"></i> %s</small></h1>", 
                        list.get(list.size() - 2),
                        list.peekLast()
                    ));
                } else if (!list.isEmpty()) {
                    out.append(String.format(
                            "<h1>%s </h1>", 
                            list.peekLast()
                        ));
                }
            } else {
                if (!list.isEmpty()) {
                    out.append(String.format(
                            "<h1>%s <small><i class=\"icon-double-angle-right\"></i> %s</small></h1>", 
                            list.peekLast(),
                            pageTitle
                        ));
                } else {
                    out.append(String.format(
                            "<h1>%s </h1>", 
                            pageTitle
                        ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return EVAL_PAGE;
    }
}
