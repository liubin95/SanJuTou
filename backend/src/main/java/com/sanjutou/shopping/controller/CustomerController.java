package com.sanjutou.shopping.controller;

import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.config.ValidatedException;
import com.sanjutou.shopping.entity.Customer;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     *
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    /**
     * customerService
     */
    @Autowired
    private CustomerService customerService;

    /**
     * 登陆方法
     *
     * @param customer 登陆用户信息
     * @return token
     */
    @PostMapping("login")
    @PassToken
    public Result<String> login(@Validated(Customer.Login.class) Customer customer, BindingResult bindingResult) throws ValidatedException {
        return customerService.customerLogin(customer);
    }
}
