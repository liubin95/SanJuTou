package com.sanjutou.shopping.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.entity.CollectionInfo;
import com.sanjutou.shopping.entity.Spu;
import com.sanjutou.shopping.mapper.CollectionInfoMapper;
import com.sanjutou.shopping.mapper.SpuMapper;
import com.sanjutou.shopping.service.CollectionInfoService;

/**
 * <p>
 * 收藏信息表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class CollectionInfoServiceImpl extends ServiceImpl<CollectionInfoMapper, CollectionInfo> implements CollectionInfoService {

    /**
     * 收藏相关mapper
     */
    private final CollectionInfoMapper collectionInfoMapper;

    /**
     *
     */
    private final SpuMapper spuMapper;

    public CollectionInfoServiceImpl(CollectionInfoMapper collectionInfoMapper, SpuMapper spuMapper) {
        this.collectionInfoMapper = collectionInfoMapper;
        this.spuMapper = spuMapper;
    }

    @Override
    public List<Spu> queryCollection(Integer id) {
        final QueryWrapper<CollectionInfo> wrapper = new QueryWrapper<>();
        wrapper.in("customer_id", id);
        wrapper.eq("type_id", 1);
        final List<CollectionInfo> collectionInfoList = collectionInfoMapper.selectList(wrapper);
        List<Spu> result;
        if (CollectionUtils.isNotEmpty(collectionInfoList)) {
            result = spuMapper.selectBatchIds(collectionInfoList.stream().map(CollectionInfo::getSidorpid).collect(Collectors.toList()));
        } else {
            result = Collections.emptyList();
        }
        return result;
    }
}
