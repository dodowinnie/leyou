package com.brandon.leyou.item.service.service;

import com.brandon.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;

import java.util.List;

public interface IBrandService {

    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    List<Brand> queryBrandByCategory(Long cid);

    void save(Brand brand, List<Long> cids);


}
