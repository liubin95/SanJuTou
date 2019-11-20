package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 收藏信息表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class CollectionInfo extends Model<CollectionInfo> {

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
     * 0代表店铺，1代表商品
     */
    private Integer typeId;

    /**
     * 店铺或商品id
     */
    private String sidorpid;


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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getSidorpid() {
        return sidorpid;
    }

    public void setSidorpid(String sidorpid) {
        this.sidorpid = sidorpid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "CollectionInfo{" +
        "id=" + id +
        ", customerId=" + customerId +
        ", typeId=" + typeId +
        ", sidorpid=" + sidorpid +
        "}";
    }
}
