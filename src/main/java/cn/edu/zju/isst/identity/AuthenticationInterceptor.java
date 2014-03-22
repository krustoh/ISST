package cn.edu.zju.isst.identity;

import java.lang.annotation.Annotation;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor  extends HandlerInterceptorAdapter {
    
    protected <A extends Annotation> A getAnnotation(Object handler, Class<A> annotationType) {
        HandlerMethod mh = (HandlerMethod) handler;
        A annotation = mh.getMethodAnnotation(annotationType);
        if (null == annotation) {
            annotation = mh.getBean().getClass().getAnnotation(annotationType);
        }
        return annotation;
    }
}
