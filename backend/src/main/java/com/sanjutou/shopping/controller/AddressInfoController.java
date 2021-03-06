package com.sanjutou.shopping.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjutou.shopping.config.CheckLogin;
import com.sanjutou.shopping.config.ValidatedException;
import com.sanjutou.shopping.entity.AddressInfo;
import com.sanjutou.shopping.service.AddressInfoService;
import com.sanjutou.shopping.util.JwtUtil;

/**
 * <p>
 * 地址信息表 前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/addressInfo")
public class AddressInfoController {

    /**
     * 地址信服务
     */
    private final AddressInfoService addressInfoService;

    @Autowired
    public AddressInfoController(AddressInfoService addressInfoService) {
        this.addressInfoService = addressInfoService;
    }

    /**
     * 查询用户的所有地址信息。
     *
     * @param token token
     * @return 集合
     */
    @GetMapping("queryAddressByCustomId")
    @CheckLogin
    public List<AddressInfo> queryAddressByCustomId(@RequestHeader String token) {
        final Integer customId = JwtUtil.getCustomerIdFromToken(token);
        return addressInfoService.queryAddressByCustomId(customId);
    }

    /**
     * 新增地址信息。
     *
     * @param token         token
     * @param addressInfo   地址信息
     * @param bindingResult 校验结果
     * @return 是否成功
     * @throws ValidatedException 如果检验失败
     */
    @PostMapping("addAddress")
    @CheckLogin
    public String addAddress(@RequestHeader String token, @Validated(AddressInfo.Insert.class) AddressInfo addressInfo, BindingResult bindingResult) throws ValidatedException {
        final Integer customId = JwtUtil.getCustomerIdFromToken(token);
        addressInfo.setCustomerId(customId);
        addressInfoService.save(addressInfo);
        if (addressInfo.getIsDefault() == 1) {
            // 自动返回id
            addressInfoService.updateDefaultAddress(customId, addressInfo.getId());
            return "默认地址更新完成";
        } else {
            return "地址新增成功";
        }
    }
}

