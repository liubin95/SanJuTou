package com.sanjutou.shopping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sanjutou.shopping.entity.AddressInfo;
import com.sanjutou.shopping.mapper.AddressInfoMapper;
import com.sanjutou.shopping.service.AddressInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     *
     */
    @Autowired
    private AddressInfoMapper addressInfoMapper;

    /**
     * @param customId
     * @return
     */
    @Override
    public List<AddressInfo> queryAddressByCustomId(Integer customId) {
        final QueryWrapper<AddressInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", customId);
        return addressInfoMapper.selectList(wrapper);
    }
}
