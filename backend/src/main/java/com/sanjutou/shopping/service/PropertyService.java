package com.sanjutou.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.Property;
import com.sanjutou.shopping.entity.vo.PropertyVO;

import java.util.List;

/**
 * <p>
 * 属性表 服务类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface PropertyService extends IService<Property> {

    /**
     * 根据spu查询属性列表
     *
     * @param spuId spuId
     * @return 属性VO集合
     */
    List<PropertyVO> queryPropertyBySpu(Integer spuId);
}
