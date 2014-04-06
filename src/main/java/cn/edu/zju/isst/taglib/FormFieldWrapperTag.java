package cn.edu.zju.isst.taglib;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.springframework.web.servlet.tags.form.AbstractDataBoundFormElementTag;
import org.springframework.web.servlet.tags.form.TagWriter;

public class FormFieldWrapperTag extends AbstractDataBoundFormElementTag implements DynamicAttributes {
    private static final long serialVersionUID = 1L;
    
    private String tag = "div";
    private String errorCssClass = "has-error";
    private Map<String, Object> attributes;
    private TagWriter tagWriter;
    
    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setErrorCssClass(String errorCssClass) {
        this.errorCssClass = errorCssClass;
    }

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        tagWriter.startTag(tag);
        if (getBindStatus().isError()) {
            if (null == attributes) {
                attributes = new HashMap<String, Object>();
                attributes.put("class", errorCssClass);
            } else {
                if (attributes.containsKey("class")) {
                    attributes.put("class", attributes.get("class") + " " + errorCssClass);
                } else {
                    attributes.put("class", errorCssClass);
                }
            }
        }
        
        if (null != attributes) {
            for (String key : attributes.keySet()) {
                tagWriter.writeOptionalAttributeValue(key, (String) attributes.get(key));
            }
        }
        
        tagWriter.forceBlock();

        
        this.tagWriter = tagWriter;
        
        return EVAL_BODY_INCLUDE;
    }
    
    public int doEndTag() throws JspException {
        this.tagWriter.endTag();
        
        attributes = null;
        
        return EVAL_PAGE;
    }
    
    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        if (null == attributes) {
            attributes = new HashMap<String, Object>();
        }
        attributes.put(localName.toLowerCase(), value);
    }
}