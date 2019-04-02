package com.brandon.leyou.item.service.service;

import com.brandon.leyou.item.service.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICategoryService {


    public List<Category> getCategoryListByParentId(Long id);

    List<Category> queryByBrandId(Long bid);

    List<String> queryNamesByIds(List<Long> ids);

}
