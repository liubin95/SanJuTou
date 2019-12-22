package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.entity.vo.PropertyVO;
import com.sanjutou.shopping.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

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
    @Autowired
    private PropertyService propertyService;

    /**
     * 根据spu查询属性列表
     *
     * @param spuId spuId
     * @return 属性VO集合
     */
    @GetMapping("queryPropertyBySpu")
    @PassToken
    public Result<List<PropertyVO>> queryPropertyBySpu(@NotNull Integer spuId) {
        final Result<List<PropertyVO>> result = new Result<>();
        result.setObj(propertyService.queryPropertyBySpu(spuId));
        return result;
    }
}

