package com.sanjutou.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.FlashSale;
import com.sanjutou.shopping.entity.Spu;
import com.sanjutou.shopping.entity.vo.SpuVO;

import java.util.List;

/**
 * <p>
 * 商品spu表 服务类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface SpuService extends IService<Spu> {

    /**
     * 根据规格id集合，返回商品集合。
     *
     * @param ids id集合
     * @return 商品集合
     */
    List<SpuVO> querySpuBySpecOptions(Integer[][] ids);

    /**
     * 根据分类id查询spu集合。
     *
     * @param id 分类id
     * @return spu集合
     */
    List<SpuVO> querySpuByCategoryId(Integer id);

    /**
     * 根据秒杀id查询参与的商品。
     *
     * @param flashSale 秒杀对象
     * @return 集合
     */
    List<SpuVO> querySpuListByFlashSale(FlashSale flashSale);

}
