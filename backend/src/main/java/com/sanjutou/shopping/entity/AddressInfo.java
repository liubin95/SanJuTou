package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * <p>
 * 地址信息表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class AddressInfo extends Model<AddressInfo> {

    private static final long serialVersionUID = 1L;

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
    private String area;

    /**
     * 镇级
     */
    private String town;


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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AddressInfo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("customerId=" + customerId)
                .add("address='" + address + "'")
                .add("mobile='" + mobile + "'")
                .add("isDefault=" + isDefault)
                .add("name='" + name + "'")
                .add("province='" + province + "'")
                .add("city='" + city + "'")
                .add("area='" + area + "'")
                .add("town='" + town + "'")
                .toString();
    }
}
