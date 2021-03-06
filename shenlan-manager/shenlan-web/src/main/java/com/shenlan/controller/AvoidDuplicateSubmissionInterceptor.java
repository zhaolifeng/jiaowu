package com.shenlan.controller;

import com.shenlan.api.Token;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by YANG on 2016/7/20.
 */
public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter {

    protected Logger logger	= LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Token annotation = method.getAnnotation(Token.class);
            if (annotation != null ) {
                boolean needSaveSession = annotation.save();
                if (needSaveSession) {
                    request.getSession( false ).setAttribute( "token" , UUID.randomUUID().toString());
                }
                boolean needRemoveSession = annotation.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        return false ;
                    }
                    request.getSession( false ).removeAttribute( "token" );
                }
            }
            return true ;
        } else {
            return super .preHandle(request, response, handler);
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession( false ).getAttribute( "token" );
        if (serverToken == null ) {
            return true ;
        }
        String clinetToken = request.getParameter( "token" );
        if (clinetToken == null ) {
            return true ;
        }
        if (!serverToken.equals(clinetToken)) {
            return true ;
        }
        return false ;
    }
}
