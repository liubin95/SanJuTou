package com.sanjutou.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.Customer;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.entity.vo.CustomerVO;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface CustomerService extends IService<Customer> {

    /**
     * 用户登陆
     *
     * @param customer 登陆用户
     * @return 成功返回token
     */
    Result<CustomerVO> customerLogin(Customer customer);

    /**
     * 检查邮箱是否已经注册。
     *
     * @param email 检测的email
     * @return true 可以注册，false 已经注册
     */
    boolean checkEmail(String email);
}
