package com.brandon.leyou.item.service.service.impl;

import com.brandon.leyou.item.service.mapper.CategoryMapper;
import com.brandon.leyou.item.service.service.ICategoryService;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getCategoryListByParentId(Long id) {
            Category record = new Category();
            record.setParentId(id);
            return categoryMapper.select(record);
    }

    @Override
    public List<Category> queryByBrandId(Long bid) {
        List<Category> categories = categoryMapper.queryByBrandId(bid);
        return categories;
    }

    @Override
    public List<String> queryNamesByIds(List<Long> ids) {
        List<String> names = categoryMapper.selectByIdList(ids).stream().map(Category::getName).collect(Collectors.toList());
        return names;
    }
}
