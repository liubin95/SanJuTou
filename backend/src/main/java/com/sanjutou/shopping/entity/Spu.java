package com.sanjutou.shopping.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * <p>
 * 商品spu表
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public class Spu extends Model<Spu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String proName;

    /**
     * 类别id，对应类别表id
     */
    private Integer cateId;

    /**
     * 商铺id，对应商铺表id
     */
    private Integer storeId;

    /**
     * 商品状态，0未上架，1已经上架
     */
    private Integer status;

    /**
     * 商品图片路径
     */
    private String cover;

    /**
     * 简介
     */
    private String info;

    /**
     * 详情
     */
    private String note;

    /**
     * 星级。
     */
    private Integer rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Spu{" +
                "id=" + id +
                ", proName=" + proName +
                ", cateId=" + cateId +
                ", storeId=" + storeId +
                ", status=" + status +
                ", cover=" + cover +
                ", info=" + info +
                ", note=" + note +
                "}";
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }
}
