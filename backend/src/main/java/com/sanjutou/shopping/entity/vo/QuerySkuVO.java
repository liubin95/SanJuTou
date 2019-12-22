package com.sanjutou.shopping.entity.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 根据属性选项查询sku的VO
 *
 * @author admin
 */
public class QuerySkuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 选项id
     */
    @NotNull
    private Integer propertyId;

    /**
     * 选项值id
     */
    @NotNull
    private Integer optionId;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    @Override
    public String toString() {
        return "QuerySkuVO{" +
                "propertyId=" + propertyId +
                ", optionId=" + optionId +
                '}';
    }
}
