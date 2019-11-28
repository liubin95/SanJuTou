package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.entity.Spu;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.entity.vo.SpecificationVO;
import com.sanjutou.shopping.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品spu表 前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

    /**
     * SpuService.
     */
    @Autowired
    private SpuService spuService;

    /**
     * @param vos
     * @return
     */
    @PostMapping("querySpuBySpecOptions")
    @PassToken
    public Result<List<Spu>> querySpuBySpecOptions(List<SpecificationVO> vos) {
        final Result<List<Spu>> result = new Result<>();
        return result;
    }
}

