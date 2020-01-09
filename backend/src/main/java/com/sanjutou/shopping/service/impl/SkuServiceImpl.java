package com.sanjutou.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.entity.FlashSale;
import com.sanjutou.shopping.entity.FlashSaleSku;
import com.sanjutou.shopping.entity.PropertyOptionSku;
import com.sanjutou.shopping.entity.Sku;
import com.sanjutou.shopping.entity.vo.QuerySkuVO;
import com.sanjutou.shopping.mapper.FlashSaleSkuMapper;
import com.sanjutou.shopping.mapper.PropertyOptionSkuMapper;
import com.sanjutou.shopping.mapper.SkuMapper;
import com.sanjutou.shopping.service.SkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品sku表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    /**
     * skuMapper
     */
    private final SkuMapper skuMapper;

    /**
     * propertyOptionSkuMapper
     */
    private final PropertyOptionSkuMapper propertyOptionSkuMapper;

    /**
     * flashSaleSkuMapper
     */
    private final FlashSaleSkuMapper flashSaleSkuMapper;

    @Autowired
    public SkuServiceImpl(SkuMapper skuMapper, PropertyOptionSkuMapper propertyOptionSkuMapper, FlashSaleSkuMapper flashSaleSkuMapper, RedisLockRegistry lockRegistry) {
        this.skuMapper = skuMapper;
        this.propertyOptionSkuMapper = propertyOptionSkuMapper;
        this.flashSaleSkuMapper = flashSaleSkuMapper;
        this.lockRegistry = lockRegistry;
    }

    /**
     * 分布式锁
     */
    private final RedisLockRegistry lockRegistry;

    private static final Logger LOGGER = LoggerFactory.getLogger(SkuServiceImpl.class);


    @Override
    public Sku querySkuByPropertyOptions(List<QuerySkuVO> list) {
        final List<List<Integer>> skuIdList = new ArrayList<>();
        // 查询符合条件的sku集合
        list.forEach(vo -> {
            final QueryWrapper<PropertyOptionSku> propertyOptionSkuQueryWrapper = new QueryWrapper<>();
            propertyOptionSkuQueryWrapper.eq("option_id", vo.getOptionId());
            final List<Integer> idList = propertyOptionSkuMapper.selectList(propertyOptionSkuQueryWrapper).stream().map(PropertyOptionSku::getSkuId).collect(Collectors.toList());
            skuIdList.add(idList);
        });
        // 交集操作
        final List<Integer> integers = skuIdList.stream().reduce((list1, list2) -> {
            list1.retainAll(list2);
            return list1;
        }).orElse(Collections.emptyList());
        // 返回响应的sku
        return integers.size() == 1 ? skuMapper.selectById(integers.get(0)) : new Sku();
    }

    @Override
    @Cacheable(cacheNames = {"sku"}, key = "#skuId")
    public Integer queryStockBySkuId(Integer skuId) throws InterruptedException {
        // 获取锁
        Integer result;
        Lock lock = lockRegistry.obtain(String.valueOf(skuId));
        if (lock.tryLock()) {
            final Sku sku = skuMapper.selectById(skuId);
            lock.unlock();
            result = sku == null ? null : sku.getStock();
        } else {
            LOGGER.info("获取锁失败：{}", skuId);
            // 休眠重试
            TimeUnit.MILLISECONDS.sleep(100);
            result = queryStockBySkuId(skuId);
        }
        return result;
    }


    @Override
    @Cacheable(cacheNames = "flash-sale-sku", key = "#skuId")
    public Integer preloadFlashSale(Integer skuId) {
        final Sku sku = skuMapper.selectById(skuId);
        return sku == null ? null : sku.getStock();
    }

    @Override
    public List<Integer> queryFlashSaleSku(FlashSale flashSale) {
        final QueryWrapper<FlashSaleSku> wrapper = new QueryWrapper<>();
        wrapper.eq("flash_id", flashSale.getId());
        final List<FlashSaleSku> list = flashSaleSkuMapper.selectList(wrapper);
        return list.stream().map(FlashSaleSku::getSkuId).collect(Collectors.toList());
    }
}
