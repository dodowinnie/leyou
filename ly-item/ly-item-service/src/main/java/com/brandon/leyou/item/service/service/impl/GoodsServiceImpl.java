package com.brandon.leyou.item.service.service.impl;

import com.brandon.leyou.common.pojo.PageResult;
import com.brandon.leyou.item.service.mapper.BrandMapper;
import com.brandon.leyou.item.service.mapper.SpuMapper;
import com.brandon.leyou.item.service.service.ICategoryService;
import com.brandon.leyou.item.service.service.IGoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private BrandMapper brandMapper;


    @Override
    public PageResult<SpuBo> querySpuByPageAndSort(Integer page, Integer rows, Boolean saleable, String key) {
        PageHelper.startPage(page, Math.min(rows,100));
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        // 是否过滤上下架
        if(saleable != null){
            criteria.orEqualTo("saleable", saleable);
        }
        // 是否模糊查询
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title", "%" + key + "%");
        }

        Page<Spu> spus = (Page<Spu>) spuMapper.selectByExample(example);
        List<SpuBo> list = spus.getResult().stream().map(spu -> {
            // 把spu变为spuBo
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);
            // 查询spu的商品分类名称，查三级分类
            List<String> names = categoryService.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            spuBo.setCname(StringUtils.join(names, "/"));
            // 查询spu品牌名称
            Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());
            spuBo.setBname(brand.getName());
            return spuBo;
        }).collect(Collectors.toList());
        return new PageResult<>(spus.getTotal(), list);
    }
}
