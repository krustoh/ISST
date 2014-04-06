package cn.edu.zju.isst.taglib;

import javax.servlet.jsp.tagext.TagSupport;

public class SetPageTitleTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    public void setLabel(String label) {
        Navigation.setPageTitle(pageContext, label);
    }
}