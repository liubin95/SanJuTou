package com.sanjutou.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.FlashSale;

/**
 * 秒杀服务接口。
 *
 * @author 刘斌
 */
public interface FlashSaleService extends IService<FlashSale> {

    /**
     * 查询最近一次的秒杀活动。
     *
     * @return 结果
     */
    FlashSale queryFlashSale();

}
