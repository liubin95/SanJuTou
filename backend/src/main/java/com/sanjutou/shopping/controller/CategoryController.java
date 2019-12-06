package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.entity.Category;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取全部分类。
     *
     * @return 全部分类集合
     */
    @GetMapping("queryAllCategory")
    @PassToken
    public Result<List<Category>> queryAllCategory() {
        final Result<List<Category>> result = new Result<>();
        result.setObj(categoryService.list());
        return result;
    }

    /**
     * 用户id】获取demo
     *
     * @param token
     * @return
     */
    public Result<List<Category>> demo(@NotBlank @RequestHeader("token") String token) {
        final Result<List<Category>> result = new Result<>();
        result.setObj(categoryService.list());
        return result;
    }

}

