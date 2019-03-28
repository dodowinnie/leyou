package com.brandon.leyou.item.service.service.impl;

import com.brandon.leyou.item.service.mapper.SpecificationMapper;
import com.brandon.leyou.item.service.service.ISpecificationService;
import com.leyou.item.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecificationServiceImpl implements ISpecificationService {
    @Autowired
    private SpecificationMapper specificationMapper;

    @Override
    public Specification queryById(Long id) {
        Specification specification = specificationMapper.selectByPrimaryKey(id);
        return specification;
    }
}
