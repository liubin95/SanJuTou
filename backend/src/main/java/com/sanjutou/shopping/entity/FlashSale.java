package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 秒杀表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class FlashSale extends Model<FlashSale> {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 状态 0 无效 1有效
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FlashSale{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", status=" + status +
                '}';
    }
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
