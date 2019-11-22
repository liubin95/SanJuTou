package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.config.WebLogAspect;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.entity.vo.SpecificationVO;
import com.sanjutou.shopping.service.SpecificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 规格表 前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

    /**
     * LOGGER.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);

    /**
     * specificationService.
     */
    @Autowired
    private SpecificationService specificationService;

    /**
     * 所有分类下规格及选项。
     *
     * @param categoryId 分类id
     * @return 集合
     */
    @GetMapping("queryCategorySpec")
    public Result<List<SpecificationVO>> qureyCategorySpec(Integer categoryId) {
        Result<List<SpecificationVO>> result = new Result<>();
        result.setObj(specificationService.queryCategorySpec(categoryId));
        return result;
    }
}

