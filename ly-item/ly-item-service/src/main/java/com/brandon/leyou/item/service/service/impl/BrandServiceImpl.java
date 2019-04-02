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
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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
            String order = sortBy + (desc ? " desc" : " asc");
            example.setOrderByClause(order);
        }
        // 查询
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
            int i = pageInfo.getPages();
        return new PageResult(pageInfo.getTotal(), new Long(pageInfo.getPages()), pageInfo.getResult());
    }

    @Override
    public List<Brand> queryBrandByCategory(Long cid) {
        return brandMapper.queryByCategoryId(cid);
    }

    @Override
    @Transactional
    public void save(Brand brand, List<Long> cids) {
        // 新增品牌信息
        brandMapper.insertSelective(brand);
        // 保存中间表
        for (Long cid : cids) {
            brandMapper.insertCategoryBrand(cid, brand.getId());
        }

    }


}
