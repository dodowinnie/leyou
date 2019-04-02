package com.brandon.leyou.item.service.mapper;

import com.leyou.item.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {

    @Select("SELECT b.* FROM tb_category_brand cb LEFT JOIN tb_brand b on cb.brand_id=b.id where cb.category_id=#{cid}")
    List<Brand> queryByCategoryId(Long cid);

    @Insert("insert into tb_category_brand (category_id, brand_id) values(#{cid}, #{bid})")
    int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long brandId);
}
