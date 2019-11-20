package com.sanjutou.shopping.service.impl;

import com.sanjutou.shopping.entity.Property;
import com.sanjutou.shopping.mapper.PropertyMapper;
import com.sanjutou.shopping.service.PropertyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 属性表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property> implements PropertyService {

}
