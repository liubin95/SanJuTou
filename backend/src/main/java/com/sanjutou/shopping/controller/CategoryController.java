package com.sanjutou.shopping.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.entity.Category;
import com.sanjutou.shopping.service.CategoryService;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    /**
     * categoryService.
     */
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 获取全部分类。
     *
     * @return 全部分类集合
     */
    @GetMapping("queryAllCategory")
    @PassToken
    public List<Category> queryAllCategory() {
        return new ArrayList<>(categoryService.list());
    }

    @GetMapping("queryCategory")
    @PassToken
    public Category queryCategory() {
        return categoryService.getById(5);
    }

}

