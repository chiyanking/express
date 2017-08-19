package com.dagemen.exception;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

public class UnionAllExceptionHandler implements ExceptionHandler {

    private static final int DEFAULT_CODE = 1;
    private static final String DEFAULT_MESSAGE = "服务器繁忙，请稍候重试";
    protected Logger logger = LoggerFactory.getLogger(UnionAllExceptionHandler.class);

    public UnionAllExceptionHandler() {
    }

    public ModelAndView getModelAndView(Exception ex, HttpServletRequest request, HandlerMethod handler) {
        if (ex instanceof ApiException) {
            ApiException e = (ApiException) ex;
            String errCode = e.getErrCode();
            String message = e.getMessage();
            if (e.isRuntime()) {
                this.loggerLog("api", errCode, message, request, e);
                return this.createJsonView(1, errCode, "服务器繁忙，请稍候重试", ex, request);
            } else {
                this.loggerLog("api", errCode, message, request, (Exception) null);
                return this.createJsonView(0, errCode, message, ex, request);
            }
        } else {
            return null;
        }
    }

    protected void loggerLog(String handler, String code, String message, HttpServletRequest request, Exception ex) {
        String query = StringUtils.trimToEmpty(request.getQueryString());
        code = StringUtils.trimToEmpty(code);
        message = StringUtils.trimToEmpty(message);
        this.logger.error("handler:{}_url:{}_query:{}_@_code:{}_message:{}", new Object[]{handler, request.getRequestURI(), query, code, message, ex});
    }

    protected ModelAndView createJsonView(int code, String errCode, String message, Exception ex, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        jsonView.addStaticAttribute("msg", message);
        if (code > 0) {
            jsonView.addStaticAttribute("code", code);
        }

        if (StringUtils.isNotBlank(errCode)) {
            jsonView.addStaticAttribute("errCode", errCode);
        }

        mav.setView(jsonView);
        return mav;
    }

    protected ModelAndView createPageView(String errCode, String message, Exception ex, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("errCode", errCode);
        mv.addObject("msg", message);
        return mv;
    }
}
