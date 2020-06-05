package com.sanjutou.shopping.controller;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.sanjutou.shopping.config.CheckLogin;
import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.config.ValidatedException;
import com.sanjutou.shopping.dictionary.Messages;
import com.sanjutou.shopping.entity.FlashSale;
import com.sanjutou.shopping.entity.OderInfo;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.entity.vo.FlashSaleVO;
import com.sanjutou.shopping.service.FlashSaleService;
import com.sanjutou.shopping.service.SkuService;
import com.sanjutou.shopping.service.SpuService;
import com.sanjutou.shopping.util.JwtUtil;
import com.sanjutou.shopping.util.SkuStockUtil;

/**
 * <p>
 * 秒杀前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/FlashSale")
public class FlashSaleController {


    /**
     * 秒杀服务。
     */
    private final FlashSaleService flashSaleService;

    /**
     * spu服务。
     */
    private final SpuService spuService;

    /**
     * sku服务。
     */
    private final SkuService skuService;

    /**
     * rabbit模板。
     */
    private final RabbitTemplate rabbitTemplate;

    /**
     * 库存工具类
     */
    private final SkuStockUtil skuStockUtil;


    /**
     * 交换机name
     */
    private static final String EXCHANGE = "skuDirectExchange";

    /**
     * 路由键
     */
    private static final String ROUTINGKEY = "FlashSaleOder";


    @Autowired
    public FlashSaleController(FlashSaleService flashSaleService, SpuService spuService, SkuService skuService, RabbitTemplate rabbitTemplate, SkuStockUtil skuStockUtil) {
        this.flashSaleService = flashSaleService;
        this.spuService = spuService;
        this.skuService = skuService;
        this.rabbitTemplate = rabbitTemplate;
        this.skuStockUtil = skuStockUtil;
    }

    /**
     * 查询秒杀的时间和商品列表。
     *
     * @return 结果
     */
    @GetMapping("queryFlashSale")
    @PassToken
    public FlashSaleVO queryFlashSale() {
        final FlashSaleVO vo = new FlashSaleVO();
        final FlashSale flashSale = flashSaleService.queryFlashSale();
        // localDateTime 转化为Date
        vo.setStartTime(Date.from(flashSale.getStartTime().atZone(ZoneId.systemDefault()).toInstant()));
        vo.setSpuList(spuService.querySpuListByFlashSale(flashSale));
        return vo;
    }

    /**
     * 秒杀预热
     */
    @GetMapping("preloadFlashSale")
    public void preloadFlashSale() {
        final FlashSale flashSale = flashSaleService.queryFlashSale();
        skuService.queryFlashSaleSku(flashSale).forEach(skuService::preloadFlashSale);
    }

    /**
     * 秒杀下单。
     *
     * @param token         token
     * @param oderInfo      订单信息
     * @param bindingResult 校验
     * @return 结果
     * @throws ValidatedException 校验失败
     */
    @PostMapping("newOder")
    @CheckLogin
    public Result newOder(@RequestHeader String token, @Validated(OderInfo.Insert.class) OderInfo oderInfo, BindingResult bindingResult) throws ValidatedException {
        Result result = new Result();
        // 库存数量
        final int stock = skuService.preloadFlashSale(oderInfo.getSkuId());
        if (oderInfo.getCounts() > stock) {
            // 购买数量大于库存数量
            result.setCodeMsg(Messages.E0006);
        } else {
            // redis更新库存
            skuStockUtil.putStock(oderInfo.getSkuId(), stock - oderInfo.getCounts());

            //token 获取用户id
            final Integer customerId = JwtUtil.getCustomerIdFromToken(token);
            oderInfo.setCustomerId(customerId);
            oderInfo.setId(IdWorker.getId());
            oderInfo.setCrateDate(LocalDateTime.now());
            // 下单并付款
            if (oderInfo.getTypeId() == 1) {
                oderInfo.setPayDate(LocalDateTime.now());
            }
            // 数据加入全局缓存
            skuStockUtil.pustOderInfo(oderInfo);
            // 数据发送到mq
            final CorrelationData correlationData = new CorrelationData();
            correlationData.setId(String.valueOf(oderInfo.getId()));
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTINGKEY, oderInfo, correlationData);

        }
        return result;
    }


}

