package com.sanjutou.shopping.mapper;

import com.sanjutou.shopping.entity.Sku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品sku表 Mapper 接口
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface SkuMapper extends BaseMapper<Sku> {

    /**
     * 查询sku，行锁
     *
     * @param skuId id
     * @return 结果
     */
    Sku querySkuById(Integer skuId);

}
