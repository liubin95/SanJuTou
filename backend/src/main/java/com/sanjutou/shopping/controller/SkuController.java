package com.sanjutou.shopping.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.entity.Sku;
import com.sanjutou.shopping.entity.vo.QuerySkuVO;
import com.sanjutou.shopping.service.SkuService;

/**
 * <p>
 * 商品sku表 前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/sku")
@Validated
public class SkuController {

    /**
     * skuService
     */
    private final SkuService skuService;

    @Autowired
    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    /**
     * 根据选项集合查询sku
     *
     * @param list 选项集合
     * @return 集合
     */
    @PostMapping("querySkuByPropertyOptions")
    @PassToken
    public Sku querySkuByPropertyOptions(@RequestBody @Valid List<QuerySkuVO> list) {
        return skuService.querySkuByPropertyOptions(list);
    }

    /**
     * 根据id查询sku详情
     *
     * @param id skuId
     * @return 对象
     */
    @GetMapping("querySkuById")
    @PassToken
    public Sku querySkuById(@NotNull Integer id) {
        return skuService.getById(id);
    }
}

