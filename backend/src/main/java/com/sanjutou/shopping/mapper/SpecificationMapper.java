package com.sanjutou.shopping.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sanjutou.shopping.entity.Specification;
import com.sanjutou.shopping.entity.dao.SpecificationDAO;

import java.util.List;

/**
 * <p>
 * 规格表 Mapper 接口
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface SpecificationMapper extends BaseMapper<Specification> {

    /**
     * 所有分类下规格及选项。
     *
     * @param categoryId 分类id
     * @return 集合
     */
    List<SpecificationDAO> queryCategorySpec(Integer categoryId);

}
