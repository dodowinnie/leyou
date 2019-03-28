package com.brandon.leyou.item.service.controller;

import com.brandon.leyou.item.service.service.ISpecificationService;
import com.leyou.item.pojo.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spec")
public class SpecifiactionController {

    @Autowired
    private ISpecificationService specificationService;

    @RequestMapping("/{id}")
    public ResponseEntity<String> querySpecificationByCategoryId(@PathVariable Long id){
        Specification specification = specificationService.queryById(id);
        if(specification == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(specification.getSpecifications());
    }

}
