package cn.edu.zju.isst.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.edu.zju.isst.common.UrlGenerator;

public class PaginationTag extends TagSupport  {
    private static final long serialVersionUID = 1L;
    
    private int total;
    private int size;
    private int page;
    private int length = 4;
    private String cssClass = "pagination";
    private String key = "page";
    private String prevCssClass = "prev";
    private String nextCssClass = "next";
    private String prevLabel = "&lt;";
    private String nextLabel = "&gt;";
    private String disabledCssClass = "disabled";
    private String activeCssClass = "active";
    private UrlGenerator urlGenerator;
    
    private String generateUrl(int page) {
        urlGenerator.setParameter(key, String.valueOf(page));
        return urlGenerator.toString();
    }
    
    @Override
    public int doEndTag () {
        if (size > 0) {
            if (null == urlGenerator) {
                urlGenerator = new UrlGenerator((HttpServletRequest) pageContext.getRequest());
            }
            
            JspWriter out = pageContext.getOut();
            int lastPage = (int) Math.ceil(1.0*total / size);
            
            page = Math.min(lastPage, page);
            page = page < 0 ? 1 : page;
            
            int prevPage = page - 1;
            int nextPage = (page == lastPage ? 0 : page + 1);

            if (lastPage >= 1) {
                try {
                    out.append(String.format("<ul class=\"%s\">", cssClass));
                    out.append(String.format(
                            "<li class=\"%s%s\"><a href=\"%s\">%s</a></li>", 
                            prevCssClass,
                            prevPage > 0 ? "" : " " + disabledCssClass,
                            prevPage > 0 ? generateUrl(prevPage) : "javascript:;", 
                            prevLabel
                    ));

                    int startPage = ((page-1)>4) ? page-4 : 1;
                    int endPage = ((startPage + length)>lastPage) ? lastPage : (startPage + length);

                    if (lastPage == endPage){
                        startPage = ((lastPage - length)>0) ? lastPage - length : 1;
                    }

                    if (startPage > 1) {
                        out.append(String.format("<li><a href=\"%s\">%s</a></li><li><span>...</span></li>", generateUrl(1), 1));
                    }

                    for (int i = startPage; i <= endPage; i++) {
                        out.append(String.format(
                                "<li class=\"%s\"><a href=\"%s\">%s</a></li>", 
                                i == page ? activeCssClass : "",
                                generateUrl(i),
                                i
                        ));
                    }

                    if (endPage < lastPage) {
                        out.append(String.format(
                                "<li><span>...</span></li><li><a href=\"%s\">%s</a></li>",
                                generateUrl(lastPage),
                                lastPage
                        ));
                    }

                    out.append(String.format(
                            "<li class=\"%s%s\"><a href=\"%s\">%s</a></li>",
                            nextCssClass,
                            nextPage > 0 ? "" : (" " + disabledCssClass),
                            nextPage > 0 ? generateUrl(nextPage) : "javascript:;",
                            nextLabel
                    ));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            urlGenerator = null;
        }
        
        return EVAL_PAGE;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPrevCssClass() {
        return prevCssClass;
    }

    public void setPrevCssClass(String prevCssClass) {
        this.prevCssClass = prevCssClass;
    }

    public String getNextCssClass() {
        return nextCssClass;
    }

    public void setNextCssClass(String nextCssClass) {
        this.nextCssClass = nextCssClass;
    }

    public String getPrevLabel() {
        return prevLabel;
    }

    public void setPrevLabel(String prevLabel) {
        this.prevLabel = prevLabel;
    }

    public String getNextLabel() {
        return nextLabel;
    }

    public void setNextLabel(String nextLabel) {
        this.nextLabel = nextLabel;
    }

    public String getDisabledCssClass() {
        return disabledCssClass;
    }

    public void setDisabledCssClass(String disabledCssClass) {
        this.disabledCssClass = disabledCssClass;
    }

    public String getActiveCssClass() {
        return activeCssClass;
    }

    public void setActiveCssClass(String activeCssClass) {
        this.activeCssClass = activeCssClass;
    }

    public UrlGenerator getUrlGenerator() {
        return urlGenerator;
    }

    public void setUrlGenerator(UrlGenerator urlGenerator) {
        this.urlGenerator = urlGenerator;
    }
}