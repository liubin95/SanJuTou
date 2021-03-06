package com.sanjutou.shopping.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.dictionary.Messages;
import com.sanjutou.shopping.entity.OderInfo;
import com.sanjutou.shopping.entity.Sku;
import com.sanjutou.shopping.entity.result.Result;
import com.sanjutou.shopping.mapper.OderInfoMapper;
import com.sanjutou.shopping.mapper.SkuMapper;
import com.sanjutou.shopping.service.OderInfoService;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class OderInfoServiceImpl extends ServiceImpl<OderInfoMapper, OderInfo> implements OderInfoService {

    /**
     * skuMapper.
     */
    private final SkuMapper skuMapper;

    private final OderInfoMapper oderInfoMapper;


    @Autowired
    public OderInfoServiceImpl(SkuMapper skuMapper, OderInfoMapper oderInfoMapper) {
        this.skuMapper = skuMapper;
        this.oderInfoMapper = oderInfoMapper;
    }

    /**
     * beforeInvocation 方法执行之后删除库存的缓存
     *
     * @param oderInfo 订单信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(cacheNames = "sku", key = "#oderInfo.skuId")
    public Result newOder(OderInfo oderInfo) {
        final Result result = new Result();
        //扣库存
        final Sku sku = skuMapper.querySkuById(oderInfo.getSkuId());
        if (sku.getStock() < oderInfo.getCounts()) {
            result.setCodeMsg(Messages.E0006);
        } else {
            // 修改库存和销量
            sku.setSales(sku.getSales() + oderInfo.getCounts());
            sku.setStock(sku.getStock() - oderInfo.getCounts());
            skuMapper.updateById(sku);
            //生成订单
            oderInfo.setTotalPrice(sku.getOldPrice().multiply(new BigDecimal(oderInfo.getCounts())));
            oderInfoMapper.insert(oderInfo);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean newFlashSaleOder(OderInfo oderInfo) {
        boolean result = true;
        final Sku sku = skuMapper.querySkuById(oderInfo.getSkuId());
        if (sku.getStock() < oderInfo.getCounts()) {
            result = false;
        } else {
            // 修改库存和销量
            sku.setSales(sku.getSales() + oderInfo.getCounts());
            sku.setStock(sku.getStock() - oderInfo.getCounts());
            skuMapper.updateById(sku);
            //生成订单
            oderInfo.setTotalPrice(sku.getNewPrice().multiply(new BigDecimal(oderInfo.getCounts())));
            oderInfo.insertOrUpdate();
        }
        return result;
    }


}
