package com.brandon.leyou.item.service.controller;

import com.brandon.leyou.common.pojo.PageResult;
import com.brandon.leyou.item.service.service.IBrandService;
import com.leyou.item.pojo.Brand;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @RequestMapping("/page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,
            @RequestParam(value = "key", required = false) String key){
        PageResult<Brand> result = brandService.queryBrandByPageAndSort(page, rows, sortBy, desc, key);
        if(result == null || result.getItems().size() == 0){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok(result);
    }

    @RequestMapping("/cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCategory(@PathVariable("cid") Long cid){
        List<Brand> brands = brandService.queryBrandByCategory(cid);
        if(CollectionUtils.isEmpty(brands)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok(brands);
    }

    @RequestMapping("/save")
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids){

        brandService.save(brand, cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }




}
