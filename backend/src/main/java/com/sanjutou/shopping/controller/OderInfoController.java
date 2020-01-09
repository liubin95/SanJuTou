package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.config.CheckLogin;
import com.sanjutou.shopping.config.ValidatedException;
import com.sanjutou.shopping.dictionary.Messages;
import com.sanjutou.shopping.entity.OderInfo;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.service.OderInfoService;
import com.sanjutou.shopping.service.SkuService;
import com.sanjutou.shopping.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/oderInfo")
public class OderInfoController {

    /**
     * oderInfoService
     */
    private final OderInfoService oderInfoService;

    /**
     * sku服务。
     */
    private final SkuService skuService;

    @Autowired
    public OderInfoController(OderInfoService oderInfoService, SkuService skuService) {
        this.oderInfoService = oderInfoService;
        this.skuService = skuService;
    }


    /**
     * 新增订单接口。
     *
     * @param token         token
     * @param oderInfo      订单信息
     * @param bindingResult 校验结果
     * @return 是否成功
     * @throws ValidatedException 检验失败异常
     */
    @PostMapping("newOder")
    @CheckLogin
    public Result<OderInfo> newOder(@RequestHeader String token, @Validated(OderInfo.Insert.class) OderInfo oderInfo, BindingResult bindingResult) throws ValidatedException, InterruptedException {
        Result<OderInfo> result = new Result<>();
        // 购买数量大于库存数量
        if (oderInfo.getCounts() > skuService.queryStockBySkuId(oderInfo.getSkuId())) {
            result.setCodeMsg(Messages.E0006);
        } else {
            //token 获取用户id
            final Integer customerId = JwtUtil.getCustomerIdFromToken(token);
            oderInfo.setCrateDate(LocalDateTime.now());
            oderInfo.setCustomerId(customerId);
            // 下单并付款
            if (oderInfo.getTypeId() == 1) {
                oderInfo.setPayDate(LocalDateTime.now());
            }
            result = oderInfoService.newOder(oderInfo);
        }
        return result;
    }
}

