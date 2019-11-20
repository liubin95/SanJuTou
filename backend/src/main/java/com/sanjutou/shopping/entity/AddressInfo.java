package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 地址信息表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class AddressInfo extends Model<AddressInfo> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer customerId;

    /**
     * 详细地址信息
     */
    private String address;

    /**
     * 电话
     */
    private String mobile;

    /**
     * 是否默认地址。0代表不是，1代表是
     */
    private Integer isDefault;

    /**
     * 收件人姓名
     */
    private String name;

    /**
     * 省级
     */
    private String province;

    /**
     * 市级
     */
    private String city;

    /**
     * 区级
     */
    private String county;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AddressInfo{" +
        "id=" + id +
        ", customerId=" + customerId +
        ", address=" + address +
        ", mobile=" + mobile +
        ", isDefault=" + isDefault +
        ", name=" + name +
        ", province=" + province +
        ", city=" + city +
        ", county=" + county +
        "}";
    }
}
