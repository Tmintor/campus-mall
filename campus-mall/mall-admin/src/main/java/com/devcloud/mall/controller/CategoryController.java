package com.devcloud.mall.controller;


import com.devcloud.mall.common.R;
import com.devcloud.mall.domain.Category;
import com.devcloud.mall.domain.dto.CategoryParentDto;
import com.devcloud.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tminto
 * @since 2022-11-01
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public R addCategory(@RequestBody Category category) {
        String id = categoryService.addCategory(category);
        return R.ok().data("id", id);
    }

    @GetMapping("/list")
    public R getAllCategory() {
        List<CategoryParentDto> list = categoryService.getAllCategory();
        return R.ok().data("allCategory", list);
    }


}

