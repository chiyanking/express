package com.dagemen.exception;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import javax.servlet.http.HttpServletRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

public interface ExceptionHandler {
    ModelAndView getModelAndView(Exception var1, HttpServletRequest var2, HandlerMethod var3);
}
