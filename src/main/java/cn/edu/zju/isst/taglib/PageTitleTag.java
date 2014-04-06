package cn.edu.zju.isst.taglib;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTitleTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String seperator = "|";
    
    public int doEndTag() {
        JspWriter out = this.pageContext.getOut();
        String pageTitle = Navigation.getPageTitle(pageContext);
        LinkedList<NavigationLink> list = Navigation.getBreadcrumbs(pageContext);
        
        try {
            boolean flag = false;
            if (null != pageTitle) {
                out.append(pageTitle);
                flag = true;
            }
            Iterator<NavigationLink> it = list.descendingIterator();
            while (it.hasNext()) {
                if (flag) {
                    out.append(" ").append(seperator).append(" ");
                }
                out.append(it.next().getLabel());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return EVAL_PAGE;
    }
}