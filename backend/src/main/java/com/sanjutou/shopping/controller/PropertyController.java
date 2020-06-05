package com.sanjutou.shopping.controller;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.entity.vo.PropertyVO;
import com.sanjutou.shopping.service.PropertyService;

/**
 * <p>
 * 属性表 前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/property")
public class PropertyController {

    /**
     * 属性服务
     */
    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    /**
     * 根据spu查询属性列表
     *
     * @param spuId spuId
     * @return 属性VO集合
     */
    @GetMapping("queryPropertyBySpu")
    @PassToken
    public List<PropertyVO> queryPropertyBySpu(@NotNull Integer spuId) {
        return propertyService.queryPropertyBySpu(spuId);
    }

    /**
     * 根据sku查询对应的属性值。
     *
     * @param skuId skuId
     * @return 属性VO
     */
    @GetMapping("queryPropertyBySku")
    @PassToken
    public List<PropertyVO> queryPropertyBySku(@NotNull Integer skuId) {
        return propertyService.queryPropertyBySku(skuId);
    }
}

