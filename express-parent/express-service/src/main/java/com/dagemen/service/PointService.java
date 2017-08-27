package com.dagemen.service;

import com.dagemen.dto.PointUpdateCompanyDTO;
import com.dagemen.entity.Company;
import com.dagemen.entity.Point;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    boolean addPointRelationCompanys(List<PointUpdateCompanyDTO> PointUpdateCompanyDTOList);
	
}
