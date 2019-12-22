package com.sanjutou.shopping.entity.vo;

import com.sanjutou.shopping.entity.Property;
import com.sanjutou.shopping.entity.PropertyOption;

import java.util.List;

/**
 * @author admin
 */
public class PropertyVO extends Property {
    private static final long serialVersionUID = 1L;

    /**
     * 对应的选项集合
     */
    private List<PropertyOption> optionList;

    public List<PropertyOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<PropertyOption> optionList) {
        this.optionList = optionList;
    }
}
