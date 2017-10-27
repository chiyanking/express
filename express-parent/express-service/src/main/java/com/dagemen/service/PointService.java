package com.dagemen.service;

import com.baomidou.mybatisplus.service.IService;
import com.dagemen.dto.PointUpdateCompanyDTO;
import com.dagemen.entity.Point;
import com.dagemen.enums.LabelValue;

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

    boolean login(Point point, HttpSession httpSession);

    boolean logOut(HttpSession httpSession);

    boolean addPointRelationCompanies(List<PointUpdateCompanyDTO> PointUpdateCompanyDTOList);

    /**
     * 获取所有的快递公司
     * @return
     */
    List<LabelValue> getAllCompanies();


    /**
     * 获取当前门店有那些快递公司
     * @return
     */
    List<LabelValue> getHasCompany();
}
