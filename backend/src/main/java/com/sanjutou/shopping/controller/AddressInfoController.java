package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.config.CheckLogin;
import com.sanjutou.shopping.entity.AddressInfo;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.service.AddressInfoService;
import com.sanjutou.shopping.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

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
     *
     */
    @Autowired
    private AddressInfoService addressInfoService;

    @GetMapping("queryAddressByCustomId")
    @CheckLogin
    public Result<List<AddressInfo>> queryAddressByCustomId(@NotNull @RequestHeader String token) {
        final Result<List<AddressInfo>> result = new Result<>();
        final Integer customId = JwtUtil.getCustomerIdFromToken(token);
        result.setObj(addressInfoService.queryAddressByCustomId(customId));
        return result;
    }
}

