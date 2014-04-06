package cn.edu.zju.isst.taglib;

import javax.servlet.jsp.tagext.TagSupport;

public class SetNavigationActiveKeyTag extends TagSupport {
    private static final long serialVersionUID = 1L;

    public void setKey(String key) {
        Navigation.setNavigationActiveKey(pageContext, key);
    }
}