package com.sanjutou.shopping.entity.vo;

import com.sanjutou.shopping.entity.Spu;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 前台展示spuVO
 *
 * @author liubin
 */
public class SpuVO extends Spu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 展示价格。
     */
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SpuVO{" +
                "price=" + price +
                "} " + super.toString();
    }
}
