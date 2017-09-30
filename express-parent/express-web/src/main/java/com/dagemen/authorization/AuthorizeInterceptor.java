package com.dagemen.authorization;

import com.dagemen.Utils.SessionHelper;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.regex.Pattern;


public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

    public static final Logger logger = LoggerFactory.getLogger(AuthorizeInterceptor.class);
    // 去除URL中的带 . 的后缀名，如 /a/b.html 转换成 /a/b
    private static Pattern URI_PATH_PATTERN = Pattern.compile("[\\.;#].*$");


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        AuthorizeAnnotation authorizeAnnotation = handlerMethod.getMethodAnnotation(AuthorizeAnnotation.class);// 从方法中获取

        //不需要验证登录
        if (authorizeAnnotation != null && !authorizeAnnotation.isLogin()) {
            return true;
        }
        //获取当前登录用户
        Object userInform = SessionHelper.getLoginPoint();
        if (userInform == null) {
            //当前没有登录
            throw new ApiException(ApiExceptionEnum.USERNOTLOGIN);
        }

        if (authorizeAnnotation == null) {
            return true;
        }
        //不需要验证权限
        if (authorizeAnnotation != null && !authorizeAnnotation.isPermission()) {
            return true;
        }
        return isAuthority(request);
    }

    /**
     * 判断是否具有权限
     *
     * @param request
     * @return
     */
    private boolean isAuthority(HttpServletRequest request) {
        //得到请求地址
        String servletPath = URI_PATH_PATTERN.matcher(request.getServletPath()).replaceAll("");
        //得到角色的所有权限
        List<String> permissionList = getUserPermission();
        //如果没有权限
        if (CollectionUtils.isEmpty(permissionList)) {
            throw new ApiException(ApiExceptionEnum.USERRIGHTERROR);
        }
        //判断是否具有权限
        for (int i = 0; i < permissionList.size(); i++) {
            Pattern pattern = Pattern.compile(permissionList.get(i) + "(/(get|do).*)?");
            if (pattern.matcher(servletPath).find()) {
                return true;
            }
        }
        throw new ApiException(ApiExceptionEnum.USERRIGHTERROR);
    }

    private List<String> getUserPermission() {
        //获取当前用户权限
        return null;
    }
}
