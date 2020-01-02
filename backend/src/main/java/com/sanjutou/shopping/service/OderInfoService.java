package com.sanjutou.shopping.service;

import com.sanjutou.shopping.entity.OderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.result.Result;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface OderInfoService extends IService<OderInfo> {

    /**
     * 普通下单
     *
     * @param oderInfo 订单信息
     * @return 订单对象
     */
    Result<OderInfo> newOder(OderInfo oderInfo);

}
