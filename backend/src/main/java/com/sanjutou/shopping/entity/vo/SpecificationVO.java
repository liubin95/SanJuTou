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

    public List<SpecificationOption> getOptions() {
        return options;
    }

    public void setOptions(List<SpecificationOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "SpecificationVO{" +
                "options=" + options +
                '}';
    }
}
