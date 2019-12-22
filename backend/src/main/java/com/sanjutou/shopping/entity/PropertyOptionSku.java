package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 属性选项和sku关联表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class PropertyOptionSku extends Model<PropertyOptionSku> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属性选项值id
     */
    private Integer optionId;

    /**
     * spuId
     */
    private Integer skuId;


    public Integer getId() {
        return id;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "PropertyOptionSku{" +
                "id=" + id +
                ", optionId=" + optionId +
                ", skuId=" + skuId +
                '}';
    }
}
