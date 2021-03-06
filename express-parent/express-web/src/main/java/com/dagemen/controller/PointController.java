package com.dagemen.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.Utils.SessionHelper;
import com.dagemen.authorization.AuthorizeAnnotation;
import com.dagemen.dto.ExpressParam;
import com.dagemen.dto.ExpressSearchDTO;
import com.dagemen.dto.PointUpdateCompanyDTO;
import com.dagemen.entity.Express;
import com.dagemen.entity.Point;
import com.dagemen.helper.TokenHelper;
import com.dagemen.service.ExpressService;
import com.dagemen.service.FileService;
import com.dagemen.service.PointService;
import com.dagemen.service.RegionProvinceService;
import org.apache.ibatis.parsing.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by 丁芙蓉 on 2017/8/19.
 */
@Controller
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @Autowired
    private ExpressService expressService;

    @Autowired
    private FileService fileService;

    @Resource
    private RegionProvinceService provinceService;

    /**
     * 登录验证
     *
     * @param point
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @AuthorizeAnnotation(isLogin = false)
    @ResponseBody
    public Object checkLogin(@RequestBody Point point, HttpSession httpSession) {
        pointService.login(point, httpSession);
        return ApiResultWrapper.success("成功");
    }

    /**
     * 获取用户登录信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getLoginForm", method = RequestMethod.GET)
    public Object getLoginForm() {
        Point loginPoint = SessionHelper.getLoginPoint();
        return ApiResultWrapper.success(loginPoint);
    }


    /**
     * 登出系统
     *
     * @param httpSession
     * @return
     */
    @RequestMapping("/logOut")
    @ResponseBody
    public Object logOut(HttpSession httpSession) {
        pointService.logOut(httpSession);
        return ApiResultWrapper.success("登出成功");
    }

    /**
     * 快递点选择自己支持的快递，及对应的模板
     *
     * @param pointUpdateCompanyDTOList
     * @return
     */
    @RequestMapping(value = "/addPointRelationCompanies", method = RequestMethod.POST)
    @ResponseBody
    public Object addPointRelationCompanies(@RequestBody List<PointUpdateCompanyDTO> pointUpdateCompanyDTOList) {

        return ApiResultWrapper.success("保存成功");
    }

    /**
     * 打印快递单子预览
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "viewFile")
    @ResponseBody
    public void viewTemplate(Long id, HttpServletResponse response) {
        fileService.viewFile(id, response);
    }

    /**
     * 生成电子面单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getElectronicSheet", method = RequestMethod.GET)
    public Map<String, Object> getElectronicSheet(Long id) {
        return ApiResultWrapper.success(fileService.getElectronicSheet(id, false));
    }

    /**
     * 强制重新生成生成电子面单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getElectronicSheetForce", method = RequestMethod.GET)
    public Map<String, Object> getElectronicSheetForce(Long id) {
        return ApiResultWrapper.success(fileService.getElectronicSheet(id, true));
    }

    /**
     * 获取快递的列表信息，分页
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getExpressList", method = RequestMethod.GET)
    public Map<String, Object> getExpressList(Page page, ExpressSearchDTO search) {
        return ApiResultWrapper.success(expressService.getExpressList(page, search));
    }

    @ResponseBody
    @RequestMapping(value = "/getExpressStatus", method = RequestMethod.GET)
    public Map<String, Object> getExpressStatus() {
        return ApiResultWrapper.success(expressService.expressStatus());
    }

    /**
     * 根据Id获取快递单详细信息
     *
     * @param expressId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getExpressById", method = RequestMethod.GET)
    public Map<String, Object> getExpressList(@RequestParam Long expressId) {
        return ApiResultWrapper.success(expressService.selectById(expressId));
    }

    @ResponseBody
    @RequestMapping(value = "/updateExpress", method = RequestMethod.POST)
    public Map<String, Object> insertExpress(@RequestBody ExpressParam express) {
        return ApiResultWrapper.success(expressService.updateExpress(express));
    }


    /**
     * 根据快递单号删除快递单
     *
     * @param express
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteExpress", method = RequestMethod.POST)
    public Map<String, Object> deleteExpressList(@RequestBody Express express) {
        return ApiResultWrapper.success(expressService.deleteById(express.getId()));
    }

    @ResponseBody
    @RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET)
    public Map<String, Object> getExpressList() {
        return ApiResultWrapper.success(pointService.getAllCompanies());
    }

    @ResponseBody
    @RequestMapping(value = "/getHasCompanies", method = RequestMethod.GET)
    @AuthorizeAnnotation(isLogin = false)
    public Map<String, Object> getHasCompanies() {
        return ApiResultWrapper.success(pointService.getHasCompany());
    }

    @ResponseBody
    @RequestMapping(value = "/getPCDTree", method = RequestMethod.GET)
    @AuthorizeAnnotation(isLogin = false)
    public Map<String, Object> getPCDTree() {
        return ApiResultWrapper.success(provinceService.getPCDTree());
    }
}

