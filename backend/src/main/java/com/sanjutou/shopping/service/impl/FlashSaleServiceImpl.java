package com.sanjutou.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.entity.FlashSale;
import com.sanjutou.shopping.mapper.FlashSaleMapper;
import com.sanjutou.shopping.service.FlashSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 秒杀服务实类。
 *
 * @author liubin
 */
@Service
public class FlashSaleServiceImpl extends ServiceImpl<FlashSaleMapper, FlashSale> implements FlashSaleService {

    /**
     * 秒杀mapper。
     */
    @Autowired
    private FlashSaleMapper flashSaleMapper;

    @Override
    public FlashSale queryFlashSale() {
        final QueryWrapper<FlashSale> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("start_time");
        wrapper.eq("status", 1);
        return flashSaleMapper.selectOne(wrapper);
    }

}
