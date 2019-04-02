package com.brandon.leyou.item.service.service;

import com.brandon.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.SpuBo;

public interface IGoodsService {

    PageResult<SpuBo> querySpuByPageAndSort(Integer page, Integer rows, Boolean saleable, String key);

}
