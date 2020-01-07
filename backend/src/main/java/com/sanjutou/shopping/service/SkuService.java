package com.sanjutou.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.FlashSale;
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


    /**
     * 查询秒杀的skuId列表，缓存预热
     *
     * @param flashSale 秒杀活动
     * @return 集合
     */
    List<Integer> queryFlashSaleSku(FlashSale flashSale);

    /**
     * 秒杀预热，把库存放入到redis的秒杀缓存中
     *
     * @param skuId skuId
     * @return 集合
     */
    Integer preloadFlashSale(Integer skuId);
}
