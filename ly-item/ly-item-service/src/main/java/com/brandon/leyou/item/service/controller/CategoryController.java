package com.brandon.leyou.item.service.controller;

import com.brandon.leyou.item.service.service.ICategoryService;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping("/list")
    public ResponseEntity<List<Category>> getCategoryListByParentId(@RequestParam(value = "pid", defaultValue = "0") Long pid){
        if(pid == null || pid.longValue() < 0){
            return ResponseEntity.badRequest().build();
        }
        List<Category> categoryList = categoryService.getCategoryListByParentId(pid);
        if(CollectionUtils.isEmpty(categoryList)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryList);

    }

    @RequestMapping("/bid/{bid}")
    public ResponseEntity<List<Category>> queryByBid(@PathVariable("bid") Long bid){
        List<Category> categories = categoryService.queryByBrandId(bid);
        if(CollectionUtils.isEmpty(categories)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categories);

    }
}
