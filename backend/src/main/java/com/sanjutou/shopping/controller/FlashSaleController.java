package com.sanjutou.shopping.controller;


import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.entity.FlashSale;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.entity.vo.FlashSaleVO;
import com.sanjutou.shopping.service.FlashSaleService;
import com.sanjutou.shopping.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 秒杀前端控制器
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/FlashSale")
public class FlashSaleController {

    /**
     *
     */
    @Autowired
    private FlashSaleService flashSaleService;

    /**
     *
     */
    @Autowired
    private SpuService spuService;

    /**
     * 查询秒杀的时间和商品列表。
     *
     * @return 结果
     */
    @GetMapping("queryFlashSale")
    @PassToken
    public Result<FlashSaleVO> queryFlashSale() {
        final Result<FlashSaleVO> result = new Result<>();
        final FlashSaleVO vo = new FlashSaleVO();
        final FlashSale flashSale = flashSaleService.queryFlashSale();
        vo.setStartTime(flashSale.getStartTime());
        vo.setSpuList(spuService.querySpuListByFlashSale(flashSale));
        result.setObj(vo);
        return result;
    }
}

