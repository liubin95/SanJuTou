package com.sanjutou.shopping.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.dictionary.Messages;
import com.sanjutou.shopping.entity.Customer;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.entity.vo.CustomerVO;
import com.sanjutou.shopping.mapper.CustomerMapper;
import com.sanjutou.shopping.service.CustomerService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    /**
     * customerMapper。
     */
    private final CustomerMapper customerMapper;
    /**
     * 密钥。
     */
    @Value("${my-config.token-secret}")
    private String secret;

    /**
     * 有效时长。
     */
    @Value("${my-config.expires-time}")
    private int expiresTime;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public Result<CustomerVO> customerLogin(Customer loginCustomer) {
        final Result<CustomerVO> result = new Result<>();
        // 根据登陆名查询用户
        final QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", loginCustomer.getLoginName());
        final Customer customer = customerMapper.selectOne(wrapper);

        // 判断
        if (customer == null) {
            result.setCodeMsg(Messages.E0003);
        } else if (!DigestUtils.md5Hex(loginCustomer.getPassword()).equalsIgnoreCase(customer.getPassword())) {
            result.setCodeMsg(Messages.E0004);
        } else {
            // 登陆成功
            final Map<String, Object> map = new HashMap<>(2);
            map.put("alg", "HMAC256");
            map.put("typ", "JWT");
            //设置过期时间
            final Calendar nowTime = Calendar.getInstance();
            nowTime.add(Calendar.DATE, expiresTime);
            final Date expiresDate = nowTime.getTime();
            //返回token
            final String token = JWT.create().withHeader(map).withClaim("customerId", customer.getId()).withIssuedAt(new Date()).withExpiresAt(expiresDate).sign(Algorithm.HMAC256(secret));
            final CustomerVO vo = new CustomerVO();
            vo.setToken(token);
            vo.setCustomerName(customer.getCustomerName());
            result.setObj(vo);
        }
        return result;
    }

    @Override
    public boolean checkEmail(String email) {
        // 根据登陆名查询用户
        final QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", email);
        final List<Customer> customerList = customerMapper.selectList(wrapper);
        return CollectionUtils.isEmpty(customerList);
    }
}
