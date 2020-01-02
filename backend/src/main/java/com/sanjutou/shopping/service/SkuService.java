package com.sanjutou.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.Sku;
import com.sanjutou.shopping.entity.vo.QuerySkuVO;

import java.util.List;

/**
 * <p>
 * 商品sku表 服务类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface SkuService extends IService<Sku> {

    /**
     * 根据选项集合查询sku
     *
     * @param list ids
     * @return 对象
     */
    Sku querySkuByPropertyOptions(List<QuerySkuVO> list);

    /**
     * 查询商品库存。
     *
     * @param skuId skuId
     * @return id无效返回null
     */
    Integer queryStockBySkuId(Integer skuId);
}
