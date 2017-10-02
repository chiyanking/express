package com.dagemen.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dagemen.dao.ExpressItemMapper;
import com.dagemen.entity.ExpressItem;
import com.dagemen.service.ExpressItemService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 快递物品 服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-10-02
 */
@Service
public class ExpressItemServiceImpl extends ServiceImpl<ExpressItemMapper, ExpressItem> implements ExpressItemService {
	
}
