package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 规格组表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class SpecificationGroup extends Model<SpecificationGroup> {

private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 规格组名称
     */
    private String specificationGroupName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecificationGroupName() {
        return specificationGroupName;
    }

    public void setSpecificationGroupName(String specificationGroupName) {
        this.specificationGroupName = specificationGroupName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SpecificationGroup{" +
        "id=" + id +
        ", specificationGroupName=" + specificationGroupName +
        "}";
    }
}
