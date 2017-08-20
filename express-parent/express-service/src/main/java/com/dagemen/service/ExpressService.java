package com.dagemen.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.dagemen.dto.ExpressSearchDTO;
import com.dagemen.entity.Express;
import com.baomidou.mybatisplus.service.IService;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
public interface ExpressService extends IService<Express> {

    Page getExpressList(Page page,ExpressSearchDTO expressSearchDTO);
	
}
