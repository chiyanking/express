package com.baomidou.com.dagemen.service.impl;

import com.baomidou.com.dagemen.entity.Point;
import com.baomidou.com.dagemen.mapper.PointMapper;
import com.baomidou.com.dagemen.service.MPPointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-20
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements MPPointService {
	
}
