package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * <p>
 * 秒杀和商品关联表表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class FlashSaleSku extends Model<FlashSaleSku> {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 秒杀id。
     */
    private Integer flashId;

    /**
     * 商品id
     */
    private Integer skuId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlashId() {
        return flashId;
    }

    public void setFlashId(Integer flashId) {
        this.flashId = flashId;
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FlashSaleSku.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("flashId=" + flashId)
                .add("skuId=" + skuId)
                .toString();
    }
}
