package com.sanjutou.shopping.entity.vo;

import com.sanjutou.shopping.entity.Property;
import com.sanjutou.shopping.entity.PropertyOption;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author admin
 */
public class PropertyVO extends Property {
    private static final long serialVersionUID = 1L;

    /**
     * 对应的选项集合。
     */
    private List<PropertyOption> optionList;

    /**
     * 对应的选项。
     */
    private PropertyOption propertyOption;

    public List<PropertyOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<PropertyOption> optionList) {
        this.optionList = optionList;
    }

    public PropertyOption getPropertyOption() {
        return propertyOption;
    }

    public void setPropertyOption(PropertyOption propertyOption) {
        this.propertyOption = propertyOption;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PropertyVO.class.getSimpleName() + "[", "]")
                .add("optionList=" + optionList)
                .add("propertyOption=" + propertyOption)
                .toString();
    }
}
