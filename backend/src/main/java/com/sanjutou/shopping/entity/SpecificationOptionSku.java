package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * sku-规格选项表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class SpecificationOptionSku extends Model<SpecificationOptionSku> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 规格选项主键
     */
    private Integer optionId;

    /**
     * sku主键
     */
    private Integer skuId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SpecificationOptionSku{" +
        "id=" + id +
        ", optionId=" + optionId +
        ", skuId=" + skuId +
        "}";
    }
}
