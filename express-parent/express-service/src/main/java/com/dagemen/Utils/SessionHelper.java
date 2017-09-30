package com.dagemen.Utils;

import com.dagemen.entity.Point;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 处理登录信息以及获取用户的登录信息
 */
public class SessionHelper {


    public static final String LOGIN_INFORM = "pointInform";

    /**
     * 登录，保存登录信息
     *
     * @param point
     * @param httpSession
     */
    public static boolean login(Point point, HttpSession httpSession) {
        point.setPassword(null);
        if (point != null) {
            httpSession.setAttribute(LOGIN_INFORM, point);
            return true;
        }
        return false;
    }

    /**
     * 登出系统，去除session
     *
     * @param httpSession
     */
    public static void logout(HttpSession httpSession) {
        httpSession.removeAttribute(LOGIN_INFORM);
    }


    public static HttpSession getHttpSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getSession();
    }

    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static Point getLoginPoint() {
        return (Point) getHttpSession().getAttribute(LOGIN_INFORM);
    }
}
