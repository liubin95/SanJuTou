package com.sanjutou.shopping.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sanjutou.shopping.entity.Specification;
import com.sanjutou.shopping.entity.SpecificationOption;
import com.sanjutou.shopping.entity.dao.SpecificationDAO;
import com.sanjutou.shopping.entity.vo.SpecificationVO;
import com.sanjutou.shopping.mapper.SpecificationMapper;
import com.sanjutou.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 规格表 服务实现类
 * </p>
 *
 * @author liubin
 * @since 2019-11-20
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements SpecificationService {

    /**
     * specificationMapper。
     */
    private final SpecificationMapper specificationMapper;

    @Autowired
    public SpecificationServiceImpl(SpecificationMapper specificationMapper) {
        this.specificationMapper = specificationMapper;
    }

    @Override
    public List<SpecificationVO> queryCategorySpec(Integer categoryId) {
        final List<SpecificationVO> result = new ArrayList<>();
        // 查询数据
        final List<SpecificationDAO> specs = specificationMapper.queryCategorySpec(categoryId);
        // 根据规格分组
        final Map<Integer, List<SpecificationDAO>> map = specs.stream().collect(Collectors.groupingBy(SpecificationDAO::getId));

        // 拼装数据
        map.forEach((id, daos) -> {
            final SpecificationVO vo = new SpecificationVO();
            vo.setId(id);
            final List<SpecificationOption> options = new ArrayList<>();
            daos.forEach(dao -> {
                vo.setSpecificationName(dao.getSpecificationName());
                final SpecificationOption temp = new SpecificationOption();
                temp.setId(dao.getOptId());
                temp.setOptionName(dao.getOptionName());
                options.add(temp);
            });
            vo.setOptions(options);
            result.add(vo);
        });
        return result;
    }
}