package com.sanjutou.shopping.service;

import com.sanjutou.shopping.entity.CollectionInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sanjutou.shopping.entity.Spu;

import java.util.List;

/**
 * <p>
 * 收藏信息表 服务类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
public interface CollectionInfoService extends IService<CollectionInfo> {

    /**
     * 获取用户全部收藏
     * @param id 用户id
     * @return 集合
     */
    List<Spu> queryCollection(Integer id);

}
