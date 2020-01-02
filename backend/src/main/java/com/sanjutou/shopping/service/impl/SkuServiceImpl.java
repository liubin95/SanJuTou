package com.sanjutou.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.entity.PropertyOptionSku;
import com.sanjutou.shopping.entity.Sku;
import com.sanjutou.shopping.entity.vo.QuerySkuVO;
import com.sanjutou.shopping.mapper.PropertyOptionSkuMapper;
import com.sanjutou.shopping.mapper.SkuMapper;
import com.sanjutou.shopping.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    @Autowired
    private SkuMapper skuMapper;

    /**
     * propertyOptionSkuMapper
     */
    @Autowired
    private PropertyOptionSkuMapper propertyOptionSkuMapper;

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
    @Cacheable(cacheNames = "sku", key = "#skuId")
    public Integer queryStockBySkuId(Integer skuId) {
        final Sku sku = skuMapper.selectById(skuId);
        return sku == null ? null : sku.getStock();
    }
}
