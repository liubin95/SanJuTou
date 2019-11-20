package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.entity.Category;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class CategoryController {

    /**
     *
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * @return
     */
    @GetMapping("queryAllCategory")
    public Result<List<Category>> queryAllCategory() {
        final Result<List<Category>> result = new Result<>();
        result.setObj(categoryService.list());
        return result;
    }

}

