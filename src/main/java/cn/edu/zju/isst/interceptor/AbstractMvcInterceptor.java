package cn.edu.zju.isst.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.view.RedirectView;

import cn.edu.zju.isst.common.WebUtils;

abstract public class AbstractMvcInterceptor extends AuthenticationInterceptor {
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (isResourceHandler(handler)) {
            return true;
        }
        
        request.setAttribute("response", response);
        
        return onPreHandle(request, response, handler);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (isResourceHandler(handler) || null == modelAndView || modelAndView.getView() instanceof RedirectView || modelAndView.getViewName().startsWith("redirect:")) {  
        } else {
            modelAndView.addObject("resourceUrl", WebUtils.createResourceUrl("/"));
            modelAndView.addObject("requestUrl", WebUtils.requestUrl());
            modelAndView.addObject("baseUrl", WebUtils.baseUrl() + "/");
            onPostHandle(request, response, handler, modelAndView);
        }
    }
    
    protected boolean isResourceHandler(Object handler) {
        return handler instanceof ResourceHttpRequestHandler;
    }
    
    protected void onPostHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    
    public boolean onPreHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
}