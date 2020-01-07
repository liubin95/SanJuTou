package com.sanjutou.shopping.util;


import com.sanjutou.shopping.entity.OderInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * 库存操作的工具类。为了使用注解 直接更新。
 * 避免RedisTemplate和cache的混用
 *
 * @author admin
 */
@Component
public class SkuStockUtil {


    /**
     * 秒杀-更新库存put
     *
     * @param skuId skuId
     * @param stock 更新的库存
     * @return 数量
     */
    @CachePut(cacheNames = "flash-sale-sku", key = "#skuId")
    public Integer putStock(@NonNull Integer skuId, @NonNull Integer stock) {
        return stock;
    }

    /**
     * 把加入mq的数据放到redis中，不成功时重试
     *
     * @param oderInfo 订单信息
     * @return 对象
     */
    @CachePut(cacheNames = "flash-sale-mq-list", key = "#oderInfo.id")
    public OderInfo pustOderInfo(OderInfo oderInfo) {
        return oderInfo;
    }

    /**
     * 成功加入mq的订单信息，从redis中删除
     *
     * @param id 订单id
     */
    @CacheEvict(cacheNames = "flash-sale-mq-list", key = "#id")
    public void evictOderInfo(Long id) {
        // 注解实现删除，方法不做任何事
    }

    @Cacheable(cacheNames = "flash-sale-mq-list", key = "#id")
    public OderInfo queryOderInfo(Long id) {
        // 注解实现删除，方法不做任何事
        return null;
    }
}
