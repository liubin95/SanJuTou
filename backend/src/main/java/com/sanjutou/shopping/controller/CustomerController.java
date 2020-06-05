package com.sanjutou.shopping.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjutou.shopping.config.CheckLogin;
import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.config.ValidatedException;
import com.sanjutou.shopping.dictionary.Messages;
import com.sanjutou.shopping.entity.Customer;
import com.sanjutou.shopping.entity.Spu;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.service.CollectionInfoService;
import com.sanjutou.shopping.service.CustomerService;
import com.sanjutou.shopping.util.JwtUtil;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {


    /**
     * customerService
     */
    private final CustomerService customerService;

    /**
     * 收藏相关服务
     */
    private final CollectionInfoService collectionInfoService;

    @Autowired
    public CustomerController(CustomerService customerService, CollectionInfoService collectionInfoService) {
        this.customerService = customerService;
        this.collectionInfoService = collectionInfoService;
    }


    /**
     * 登录。
     *
     * @param customer      登录用户
     * @param bindingResult 校验对象
     * @return 结果
     * @throws ValidatedException 校验异常
     */
    @PostMapping("login")
    @PassToken
    public Result login(@Validated(Customer.Login.class) Customer customer, BindingResult bindingResult) throws ValidatedException {
        return customerService.customerLogin(customer);
    }

    /**
     * 注册。
     *
     * @param customer      注册用户
     * @param bindingResult 校验对象
     * @return 结果
     * @throws ValidatedException 校验异常
     */
    @PostMapping("reg")
    @PassToken
    public Result reg(@Validated(Customer.Reg.class) Customer customer, BindingResult bindingResult) throws ValidatedException {
        Result result = new Result();
        // 判断是否可以注册
        if (customerService.checkEmail(customer.getLoginName())) {
            // 加密密码
            final String password = customer.getPassword();
            customer.setPassword(DigestUtils.md5Hex(customer.getPassword()));
            customer.setRegTime(LocalDateTime.now());
            customerService.save(customer);
            // 登录
            customer.setPassword(password);
            result = customerService.customerLogin(customer);
        } else {
            result.setCodeMsg(Messages.E0005);
        }
        return result;
    }

    /**
     * 检查登录。
     *
     * @param token token
     * @return 是否有效
     */
    @GetMapping("checkLogin")
    @CheckLogin
    public String checkLogin(@RequestHeader String token) {
        return "";
    }


    /**
     * 获取用户的全部收藏
     *
     * @param token token
     * @return 集合
     */
    @GetMapping("queryCollection")
    @CheckLogin
    public List<Spu> queryCollection(@RequestHeader String token) {
        return collectionInfoService.queryCollection(JwtUtil.getCustomerIdFromToken(token));
    }
}
