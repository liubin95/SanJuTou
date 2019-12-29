package com.sanjutou.shopping.entity.vo;

import com.sanjutou.shopping.entity.Customer;

/**
 * 用户VO
 *
 * @author admin
 */
public class CustomerVO extends Customer {

    private static final long serialVersionUID = 1L;

    /**
     * token.
     */
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CustomerVO{" +
                "token='" + token + '\'' +
                "} " + super.toString();
    }
}
