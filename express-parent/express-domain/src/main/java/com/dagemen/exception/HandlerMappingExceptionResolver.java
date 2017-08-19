package com.dagemen.exception;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class HandlerMappingExceptionResolver extends SimpleMappingExceptionResolver implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(HandlerMappingExceptionResolver.class);
    private static final String HANDLER_FLAG = "handler:";
    private ApplicationContext applicationContext;

    public HandlerMappingExceptionResolver() {
    }

    protected void logException(Exception ex, HttpServletRequest request) {
    }

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String viewName = this.determineViewName(ex, request);
        if (viewName != null) {
            Integer statusCode = this.determineStatusCode(request, viewName);
            if (statusCode != null) {
                this.applyStatusCodeIfPossible(request, response, statusCode.intValue());
            }

            HandlerMethod handlerMethod = handler instanceof HandlerMethod ? (HandlerMethod) handler : null;
            return this.getModelAndView(viewName, ex, request, handlerMethod);
        } else {
            return null;
        }
    }

    protected ModelAndView getModelAndView(String viewName, Exception ex, HttpServletRequest request, HandlerMethod handler) {
        if (StringUtils.startsWith(viewName, "handler:")) {
            try {
                ExceptionHandler exceptionHandler = (ExceptionHandler) this.applicationContext.getBean(StringUtils.removeStart(viewName, "handler:"), ExceptionHandler.class);
                return exceptionHandler.getModelAndView(ex, request, handler);
            } catch (BeansException var6) {
                logger.error("找不到指定的异常处理对象", var6);
                return null;
            }
        } else {
            return super.getModelAndView(viewName, ex, request);
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
