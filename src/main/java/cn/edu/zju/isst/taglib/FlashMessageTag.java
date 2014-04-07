package cn.edu.zju.isst.taglib;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.edu.zju.isst.common.AppConfig;
import cn.edu.zju.isst.common.FlashMessage;

public class FlashMessageTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    
    public int doEndTag() throws JspException {
        HttpSession session = this.pageContext.getSession();
        FlashMessage fm = (FlashMessage) session.getAttribute(AppConfig.FLASH_MESSAGE_KEY);
        if (null != fm) {
            JspWriter out = this.pageContext.getOut();
            try {
                out.append(String.format(
                        "<div class=\"alert alert-block alert-%s\"><button type=\"button\" class=\"close\" data-dismiss=\"alert\">", 
                        fm.getStatus().equals("error") ? "danger" : fm.getStatus()
                ));
                out.append(String.format("<i class=\"icon-remove\"></i></button>%s</div>", fm.getMessage()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            session.removeAttribute(AppConfig.FLASH_MESSAGE_KEY);
        }
        
        return EVAL_PAGE;
    }
}