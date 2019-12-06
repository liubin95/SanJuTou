package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

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
    private Integer spuId;

    @Override
    public String toString() {
        return "FlashSaleSku{" +
                "id=" + id +
                ", flashId=" + flashId +
                ", spuId=" + spuId +
                "} ";
    }

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
}
