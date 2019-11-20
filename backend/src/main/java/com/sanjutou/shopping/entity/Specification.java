package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 规格表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class Specification extends Model<Specification> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 规格名称
     */
    private String specificationName;

    /**
     * 规格组主键
     */
    private Integer groupId;

    /**
     * 类别主键
     */
    private Integer cateId;

    /**
     * 排序
     */
    private Integer order;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Specification{" +
        "id=" + id +
        ", specificationName=" + specificationName +
        ", groupId=" + groupId +
        ", cateId=" + cateId +
        ", order=" + order +
        "}";
    }
}
