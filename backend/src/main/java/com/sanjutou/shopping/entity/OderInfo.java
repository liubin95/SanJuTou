package com.sanjutou.shopping.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class OderInfo extends Model<OderInfo> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 0代表待付款，1代表已付款，2已收货
     */
    private Integer typeId;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 商品id
     */
    private String skuId;

    /**
     * 商品数量
     */
    private Integer counts;

    /**
     * 创建时间
     */
    private LocalDateTime crateDate;

    /**
     * 付款时间
     */
    private LocalDateTime payDate;

    /**
     * 收货时间
     */
    private LocalDateTime doneDate;

    /**
     * 收货地址id
     */
    private Integer addressId;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 买家留言
     */
    private String note;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public LocalDateTime getCrateDate() {
        return crateDate;
    }

    public void setCrateDate(LocalDateTime crateDate) {
        this.crateDate = crateDate;
    }

    public LocalDateTime getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDateTime payDate) {
        this.payDate = payDate;
    }

    public LocalDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "OderInfo{" +
        "id=" + id +
        ", typeId=" + typeId +
        ", customerId=" + customerId +
        ", skuId=" + skuId +
        ", counts=" + counts +
        ", crateDate=" + crateDate +
        ", payDate=" + payDate +
        ", doneDate=" + doneDate +
        ", addressId=" + addressId +
        ", totalPrice=" + totalPrice +
        ", note=" + note +
        "}";
    }
}