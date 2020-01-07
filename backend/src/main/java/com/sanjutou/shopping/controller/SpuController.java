package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.entity.Spu;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.entity.vo.SpuVO;
import com.sanjutou.shopping.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@Validated
public class SpuController {

    /**
     * SpuService.
     */
    private final SpuService spuService;

    @Autowired
    public SpuController(SpuService spuService) {
        this.spuService = spuService;
    }

    /**
     * 根据规格id集合，返回商品集合。
     *
     * @param ids 规格选项id集合
     * @return spu集合
     */
    @PostMapping("querySpuBySpecOptions")
    @PassToken
    public Result<List<SpuVO>> querySpuBySpecOptions(@NotEmpty @RequestBody Integer[][] ids) {
        final Result<List<SpuVO>> result = new Result<>();
        result.setObj(spuService.querySpuBySpecOptions(ids));
        return result;
    }

    /**
     * 根据分类id查询spu集合。
     *
     * @param categoryId 分类id
     * @return spu集合
     */
    @GetMapping("querySpuByCategoryId")
    @PassToken
    public Result<List<SpuVO>> querySpuByCategoryId(@NotNull Integer categoryId) {
        final Result<List<SpuVO>> result = new Result<>();
        result.setObj(spuService.querySpuByCategoryId(categoryId));
        return result;
    }

    /**
     * 根据商品id，获取详情
     *
     * @param spuId 商品id
     * @return sku 集合
     */
    @GetMapping("querySpuById")
    @PassToken
    public Result<Spu> querySpuById(@NotNull Integer spuId) {
        final Result<Spu> result = new Result<>();
        final Spu spu = spuService.getById(spuId);
        result.setObj(spu);
        return result;
    }

    /**
     * 根据skuId查询spu信息
     *
     * @param skuId sku的id
     * @return spu对象
     */
    @GetMapping("querySpuBySku")
    @PassToken
    public Result<Spu> querySpuBySku(@NotNull Integer skuId) {
        final Result<Spu> result = new Result<>();
        result.setObj(spuService.querySpuBySku(skuId));
        return result;
    }
}

