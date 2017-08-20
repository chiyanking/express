package com.dagemen.service;

import com.dagemen.entity.Point;
import com.baomidou.mybatisplus.service.IService;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
public interface PointService extends IService<Point> {

    boolean checkLogin(Point point, HttpSession httpSession);

    boolean logOut(HttpSession httpSession);
	
}
