package com.brandon.leyou.item.service.service;

import com.brandon.leyou.item.service.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    public List<Category> getCategoryListByParentId(Long id){
        Category record = new Category();
        record.setParentId(id);
        return categoryMapper.select(record);
    }


}
