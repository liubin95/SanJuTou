package com.sanjutou.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.entity.Property;
import com.sanjutou.shopping.entity.PropertyOption;
import com.sanjutou.shopping.entity.PropertyOptionSku;
import com.sanjutou.shopping.entity.Spu;
import com.sanjutou.shopping.entity.vo.PropertyVO;
import com.sanjutou.shopping.mapper.PropertyMapper;
import com.sanjutou.shopping.mapper.PropertyOptionMapper;
import com.sanjutou.shopping.mapper.PropertyOptionSkuMapper;
import com.sanjutou.shopping.mapper.SpuMapper;
import com.sanjutou.shopping.service.PropertyService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 属性表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property> implements PropertyService {

    /**
     * propertyMapper
     */
    private final PropertyMapper propertyMapper;

    /**
     * propertyOptionMapper
     */
    private final PropertyOptionMapper propertyOptionMapper;

    /**
     * spuMapper
     */
    private final SpuMapper spuMapper;

    /**
     * 属性和sku关联的mapper。
     */
    private final PropertyOptionSkuMapper propertyOptionSkuMapper;

    @Autowired
    public PropertyServiceImpl(PropertyMapper propertyMapper, PropertyOptionMapper propertyOptionMapper, SpuMapper spuMapper, PropertyOptionSkuMapper propertyOptionSkuMapper) {
        this.propertyMapper = propertyMapper;
        this.propertyOptionMapper = propertyOptionMapper;
        this.spuMapper = spuMapper;
        this.propertyOptionSkuMapper = propertyOptionSkuMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PropertyVO> queryPropertyBySpu(Integer spuId) {
        final List<PropertyVO> result = new ArrayList<>();
        // 获取到商品的类别
        final Spu spu = spuMapper.selectById(spuId);
        // 获取分类下属性列表
        final QueryWrapper<Property> propertyQueryWrapper = new QueryWrapper<>();
        propertyQueryWrapper.eq("cate_id", spu.getCateId());
        final List<Property> propertyList = propertyMapper.selectList(propertyQueryWrapper);
        if (CollectionUtils.isNotEmpty(propertyList)) {
            final List<Integer> propertyIdList = propertyList.stream().map(Property::getId).collect(Collectors.toList());
            // 获取属性下属性值选项
            final QueryWrapper<PropertyOption> propertyOptionQueryWrapper = new QueryWrapper<>();
            propertyOptionQueryWrapper.and(q -> q.in("property_id", propertyIdList).eq("spu_id", spuId));
            // 根据属性id分组
            final Map<Integer, List<PropertyOption>> idPropertyMap = propertyOptionMapper.selectList(propertyOptionQueryWrapper).stream().collect(Collectors.groupingBy(PropertyOption::getPropertyId));
            propertyList.forEach(property -> {
                // 组合数据
                final PropertyVO vo = new PropertyVO();
                BeanUtils.copyProperties(property, vo);
                vo.setOptionList(idPropertyMap.getOrDefault(property.getId(), new ArrayList<>()));
                result.add(vo);
            });
        }
        return result;
    }

    @Override
    public List<PropertyVO> queryPropertyBySku(Integer skuId) {
        final List<PropertyVO> result = new ArrayList<>();
        // 查询属性和sku关联表数据
        final QueryWrapper<PropertyOptionSku> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sku_id", skuId);
        final List<PropertyOptionSku> skuList = propertyOptionSkuMapper.selectList(queryWrapper);
        // 查询属性选项表数据
        final List<PropertyOption> propertyOptions = propertyOptionMapper.selectBatchIds(skuList.stream().map(PropertyOptionSku::getOptionId).collect(Collectors.toList()));
        final Map<Integer, PropertyOption> map = propertyOptions.stream().collect(Collectors.toMap(PropertyOption::getPropertyId, o -> o));
        // 查询属性表数据
        final List<Property> propertyList = propertyMapper.selectBatchIds(propertyOptions.stream().map(PropertyOption::getPropertyId).collect(Collectors.toList()));
        propertyList.forEach(property -> {
            // 组合数据
            final PropertyVO vo = new PropertyVO();
            BeanUtils.copyProperties(property, vo);
            vo.setPropertyOption(map.getOrDefault(property.getId(), new PropertyOption()));
            result.add(vo);
        });
        return result;
    }
}
