package com.sanjutou.shopping.entity.vo;

import com.sanjutou.shopping.entity.Specification;
import com.sanjutou.shopping.entity.SpecificationOption;

import java.io.Serializable;
import java.util.List;

/**
 * Specification展示对象
 *
 * @author libuin
 */
public class SpecificationVO extends Specification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 规格对应的选项值列表。
     */
    private List<SpecificationOption> options;

    /**
     * 规格对应的选项id列表.
     */
    private List<Integer> optionIds;

    public List<SpecificationOption> getOptions() {
        return options;
    }

    public void setOptions(List<SpecificationOption> options) {
        this.options = options;
    }

    public List<Integer> getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(List<Integer> optionIds) {
        this.optionIds = optionIds;
    }

    @Override
    public String toString() {
        return "SpecificationVO{" +
                "options=" + options +
                ", optionIds=" + optionIds +
                '}';
    }
}
