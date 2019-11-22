package com.sanjutou.shopping.entity.dao;

import com.sanjutou.shopping.entity.Specification;

/**
 * Specification数据库对象。
 *
 * @author liubin
 */
public class SpecificationDAO extends Specification {

    /**
     * 选项id
     */
    private Integer optId;
    /**
     * 选项名
     */
    private String optionName;

    public Integer getOptId() {
        return optId;
    }

    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }
}
