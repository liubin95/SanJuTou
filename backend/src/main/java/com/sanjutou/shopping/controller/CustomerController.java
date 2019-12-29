package com.sanjutou.shopping.controller;

import com.sanjutou.shopping.config.CheckLogin;
import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.config.ValidatedException;
import com.sanjutou.shopping.dictionary.Messages;
import com.sanjutou.shopping.entity.Customer;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.entity.vo.CustomerVO;
import com.sanjutou.shopping.service.CustomerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

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
    @Autowired
    private CustomerService customerService;


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
    public Result<CustomerVO> login(@Validated(Customer.Login.class) Customer customer, BindingResult bindingResult) throws ValidatedException {
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
    public Result<CustomerVO> reg(@Validated(Customer.Reg.class) Customer customer, BindingResult bindingResult) throws ValidatedException {
        Result<CustomerVO> result = new Result<>();
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
    public Result<String> checkLogin(@RequestHeader @NotBlank String token) {
        return new Result<>();
    }
}
