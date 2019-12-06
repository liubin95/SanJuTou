package com.sanjutou.shopping.entity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 秒杀用VO
 *
 * @author 刘斌
 */
public class FlashSaleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 商品列表
     */
    private List<SpuVO> spuList;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<SpuVO> getSpuList() {
        return spuList;
    }

    public void setSpuList(List<SpuVO> spuList) {
        this.spuList = spuList;
    }

    @Override
    public String toString() {
        return "FlashSaleVO{" +
                "startTime=" + startTime +
                ", spuList=" + spuList +
                '}';
    }
}
