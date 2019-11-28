package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class SkuController {

    /**
     *
     */
    @Autowired
    private SkuService skuService;


}

