package com.sanjutou.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.Specification;
import com.sanjutou.shopping.entity.vo.SpecificationVO;

import java.util.List;

/**
 * <p>
 * 规格表 服务类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface SpecificationService extends IService<Specification> {

    /**
     * 所有分类下规格及选项。
     *
     * @param categoryId 分类id
     * @return 集合
     */
    List<SpecificationVO> queryCategorySpec(Integer categoryId);

}
