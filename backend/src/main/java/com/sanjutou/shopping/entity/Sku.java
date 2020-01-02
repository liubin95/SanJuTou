package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 商品sku表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Component
public class Sku extends Model<Sku> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 正常价格
     */
    private BigDecimal oldPrice;

    /**
     * 促销价格
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

    /**
     * 描述
     */
    private String info;

    /**
     * 静态路径映射前缀
     */
    @TableField(exist = false)
    private static String prefix;

    @Value("${my-config.resource-prefix}")
    public void setSecret(String prefix) {
        Sku.prefix = prefix;
    }

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
        if (cover.startsWith(prefix)) {
            this.cover = cover;
        } else {
            this.cover = prefix + cover;
        }
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
