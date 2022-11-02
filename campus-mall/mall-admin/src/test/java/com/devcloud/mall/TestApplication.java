package com.devcloud.mall;

import com.devcloud.mall.domain.dto.CategoryParentDto;
import com.devcloud.mall.mapper.CategoryMapper;
import com.devcloud.mall.mapper.GoodsMapper;
import com.devcloud.mall.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 吴员外
 * @date 2022/11/2 1:08
 */
@SpringBootTest
public class TestApplication {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Test
    void test() {
        //List<CategoryParentDto> allCategory = categoryService.getAllCategory();
        //System.out.println(allCategory);
        goodsMapper.selectCategory().forEach(System.out::println);
    }

}
