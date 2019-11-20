package com.sanjutou.shopping.service.impl;

import com.sanjutou.shopping.entity.Category;
import com.sanjutou.shopping.mapper.CategoryMapper;
import com.sanjutou.shopping.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
