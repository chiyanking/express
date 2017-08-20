package com.dagemen.Utils;

import com.dagemen.entity.Point;
import com.dagemen.entity.User;

import javax.servlet.http.HttpSession;

/**
 * Created by 丁芙蓉 on 2017/8/20.
 */
public class LoginSessionHelper {

    /**
     *  登录，保存登录信息
     * @param point
     * @param httpSession
     */
    public static void logIn(Point point, User user, HttpSession httpSession) {
        point.setPassword(null);
        if(point != null){
            httpSession.setAttribute("pointInfor", point);
        }
        if(user != null){
            httpSession.setAttribute("userInfor", user);
        }
    }

    /**
     * 登出系统，去除session
     * @param httpSession
     */
    public static void logOut(HttpSession httpSession){
        httpSession.removeAttribute("pointInfor");
        httpSession.removeAttribute("userInfor");
    }


}
