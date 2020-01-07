package com.sanjutou.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.entity.AddressInfo;
import com.sanjutou.shopping.mapper.AddressInfoMapper;
import com.sanjutou.shopping.service.AddressInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 地址信息表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class AddressInfoServiceImpl extends ServiceImpl<AddressInfoMapper, AddressInfo> implements AddressInfoService {

    /**
     * 地址信息mapper。
     */
    private final AddressInfoMapper addressInfoMapper;

    @Autowired
    public AddressInfoServiceImpl(AddressInfoMapper addressInfoMapper) {
        this.addressInfoMapper = addressInfoMapper;
    }

    @Override
    public List<AddressInfo> queryAddressByCustomId(Integer customId) {
        final QueryWrapper<AddressInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", customId);
        wrapper.ne("is_default", 2);
        return addressInfoMapper.selectList(wrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDefaultAddress(Integer customId, Integer addressId) {
        // 修改用户所有地址为不默认
        final AddressInfo addressInfo = new AddressInfo();
        addressInfo.setIsDefault(0);
        final UpdateWrapper<AddressInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("customer_id", customId);
        addressInfoMapper.update(addressInfo, wrapper);
        // 设置新的默认地址
        addressInfo.setId(addressId);
        addressInfo.setIsDefault(1);
        addressInfoMapper.updateById(addressInfo);
    }
}
