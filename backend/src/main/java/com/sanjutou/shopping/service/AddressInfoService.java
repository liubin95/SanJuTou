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
     * 查询用户的所有地址信息。
     *
     * @param customId 用户id
     * @return 集合
     */
    List<AddressInfo> queryAddressByCustomId(Integer customId);

    /**
     * 更新默认地址
     *
     * @param customId  用户id
     * @param addressId 地址id
     */
    void updateDefaultAddress(Integer customId, Integer addressId);
}
