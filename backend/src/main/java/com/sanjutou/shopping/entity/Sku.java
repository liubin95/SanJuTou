package com.sanjutou.shopping.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 商品sku表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class Sku extends Model<Sku> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 原价
     */
    private BigDecimal oldPrice;

    /**
     * 现价
     */
    private BigDecimal newPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 商品图片路径
     */
    private String cover;

    /**
     * 月销售量
     */
    private Integer sales;

    /**
     * spu主键
     */
    private Integer spuId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sku{" +
        "id=" + id +
        ", oldPrice=" + oldPrice +
        ", newPrice=" + newPrice +
        ", stock=" + stock +
        ", cover=" + cover +
        ", sales=" + sales +
        ", spuId=" + spuId +
        "}";
    }
}
