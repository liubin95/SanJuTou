package com.sanjutou.shopping.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.AddressInfo;

import java.util.List;

/**
 * <p>
 * 地址信息表 服务类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface AddressInfoService extends IService<AddressInfo> {

    /**
     * @param customId
     * @return
     */
    List<AddressInfo> queryAddressByCustomId(Integer customId);
}
