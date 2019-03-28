package com.brandon.leyou.item.service.service.impl;

import com.brandon.leyou.common.pojo.PageResult;
import com.brandon.leyou.item.service.mapper.BrandMapper;
import com.brandon.leyou.item.service.service.IBrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class BrandServiceImpl implements IBrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotEmpty(key)) {
            example.createCriteria().andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }
        // 排序
        if (StringUtils.isNotEmpty(key)) {
            String order = sortBy + (desc ? "desc" : "asc");
            example.setOrderByClause(order);
        }
        // 查询
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
            int i = pageInfo.getPages();
        return new PageResult(pageInfo.getTotal(), new Long(pageInfo.getPages()), pageInfo.getResult());
    }


}
