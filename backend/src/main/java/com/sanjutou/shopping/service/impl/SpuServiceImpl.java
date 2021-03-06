package com.sanjutou.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.entity.FlashSale;
import com.sanjutou.shopping.entity.FlashSaleSku;
import com.sanjutou.shopping.entity.Sku;
import com.sanjutou.shopping.entity.SpecificationOptionSku;
import com.sanjutou.shopping.entity.Spu;
import com.sanjutou.shopping.entity.vo.SpuVO;
import com.sanjutou.shopping.mapper.FlashSaleSkuMapper;
import com.sanjutou.shopping.mapper.SkuMapper;
import com.sanjutou.shopping.mapper.SpecificationOptionSkuMapper;
import com.sanjutou.shopping.mapper.SpuMapper;
import com.sanjutou.shopping.service.SpuService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品spu表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {


    /**
     * spuMapper.
     */
    private final SpuMapper spuMapper;

    /**
     * skuMapper.
     */
    private final SkuMapper skuMapper;

    /**
     * 规格选项的mapper.
     */
    private final SpecificationOptionSkuMapper specificationOptionSkuMapper;

    /**
     * 秒杀mapper
     */
    private final FlashSaleSkuMapper flashSaleSkuMapper;

    @Autowired
    public SpuServiceImpl(SpuMapper spuMapper, SkuMapper skuMapper, SpecificationOptionSkuMapper specificationOptionSkuMapper, FlashSaleSkuMapper flashSaleSkuMapper) {
        this.spuMapper = spuMapper;
        this.skuMapper = skuMapper;
        this.specificationOptionSkuMapper = specificationOptionSkuMapper;
        this.flashSaleSkuMapper = flashSaleSkuMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpuVO> querySpuBySpecOptions(Integer[][] ids) {
        // 交集前id集合
        final List<List<Integer>> beforeList = new ArrayList<>();
        // 查询数据
        Arrays.stream(ids).forEach(integers -> {
            if (integers.length != 0) {
                final QueryWrapper<SpecificationOptionSku> wrapper = new QueryWrapper<>();
                wrapper.in("option_id", Arrays.asList(integers));
                //获取到全部OptionSku
                final List<SpecificationOptionSku> optionSkuList = specificationOptionSkuMapper.selectList(wrapper);
                // 获取skuID 去重
                final List<Integer> skuIds = optionSkuList.stream()
                        .map(SpecificationOptionSku::getSkuId)
                        .distinct()
                        .collect(Collectors.toList());
                beforeList.add(skuIds);
            }
        });
        // 交集操作
        final List<Integer> afterList = beforeList.stream().reduce((list1, list2) -> {
            list1.retainAll(list2);
            return list1;
        }).orElse(Collections.emptyList());
        List<SpuVO> result;
        // sku可能不存在
        if (CollectionUtils.isEmpty(afterList)) {
            result = Collections.emptyList();
        } else {
            // 获取全部sku
            final List<Sku> skuList = skuMapper.selectBatchIds(afterList);
            final Map<Integer, BigDecimal> idSkuMap = skuList.stream()
                    .collect(Collectors.toMap(Sku::getSpuId, Sku::getNewPrice, (o1, o2) -> o2));
            // 获取全部spu
            final List<Spu> spuList = spuMapper.selectBatchIds(idSkuMap.keySet());
            result = voAddPrice(spuList, skuList, false);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SpuVO> querySpuByCategoryId(Integer id) {
        // 获取分类下全部有效的spu
        final QueryWrapper<Spu> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(q -> q.eq("cate_id", id).eq("status", 1));
        final List<Spu> spuList = spuMapper.selectList(queryWrapper);
        // 增加价格属性
        return voAddPrice(spuList, null, false);
    }

    @Override
    public List<SpuVO> querySpuListByFlashSale(FlashSale flashSale) {
        // 查询秒杀活动
        final QueryWrapper<FlashSaleSku> wrapper = new QueryWrapper<>();
        wrapper.eq("flash_id", flashSale.getId());
        final List<FlashSaleSku> list = flashSaleSkuMapper.selectList(wrapper);
        List<SpuVO> result;
        if (CollectionUtils.isNotEmpty(list)) {
            final List<Integer> skuIdList = list.stream().map(FlashSaleSku::getSkuId).collect(Collectors.toList());
            // 查询对应的sku
            final List<Sku> skuList = skuMapper.selectBatchIds(skuIdList);
            // 查询对应spu
            final List<Spu> spuList = spuMapper.selectBatchIds(skuList.stream().map(Sku::getSpuId).collect(Collectors.toList()));
            result = voAddPrice(spuList, skuList, true);
        } else {
            result = Collections.emptyList();
        }
        return result;

    }

    @Override
    public Spu querySpuBySku(Integer skuId) {
        final Sku sku = skuMapper.selectById(skuId);
        return sku == null ? new Spu() : spuMapper.selectById(sku.getSpuId());
    }

    /**
     * 把sku的价格 增加对应的SpuVO
     *
     * @param spuList    spuList
     * @param skuList    skuList 为空时，对价格不敏感，首页展示用
     * @param isNewPrice 是否为促销价格
     * @return spuVO 集合
     */
    private List<SpuVO> voAddPrice(List<Spu> spuList, List<Sku> skuList, boolean isNewPrice) {
        if (CollectionUtils.isEmpty(skuList)) {
            // spuID 集合
            final List<Integer> spuIdList = spuList.stream()
                    .map(Spu::getId)
                    .collect(Collectors.toList());
            final QueryWrapper<Sku> skuQueryWrapper = new QueryWrapper<>();
            skuQueryWrapper.in("spu_id", spuIdList);
            // 获取sku
            skuList = skuMapper.selectList(skuQueryWrapper);
        }
        Map<Integer, BigDecimal> idSkuMap;
        if (isNewPrice) {
            idSkuMap = skuList.stream().collect(Collectors.toMap(Sku::getSpuId, Sku::getNewPrice, (o1, o2) -> o1.compareTo(o2) > 0 ? o1 : o2));
        } else {
            idSkuMap = skuList.stream().collect(Collectors.toMap(Sku::getSpuId, Sku::getOldPrice, (o1, o2) -> o1.compareTo(o2) > 0 ? o1 : o2));
        }
        //整合数据
        return spuList.stream().map(vo -> {
            final SpuVO temp = new SpuVO();
            BeanUtils.copyProperties(vo, temp);
            temp.setPrice(idSkuMap.getOrDefault(vo.getId(), BigDecimal.valueOf(9999999999.00)));
            return temp;
        }).collect(Collectors.toList());
    }
}
