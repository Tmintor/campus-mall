package com.devcloud.mall.controller;


import com.devcloud.mall.common.R;
import com.devcloud.mall.domain.Category;
import com.devcloud.mall.domain.dto.CategoryParentDto;
import com.devcloud.mall.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
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
    @ApiOperation("添加分类")
    public R addCategory(@RequestBody Category category) {
        String id = categoryService.addCategory(category);
        return R.ok().data("id", id);
    }

    @GetMapping("/list")
    @ApiOperation("获取所有分类")
    public R getAllCategory() {
        List<CategoryParentDto> list = categoryService.getAllCategory();
        return R.ok().data("allCategory", list);
    }

    @PutMapping("")
    @ApiOperation("修改分类")
    public R modifyCategory(@RequestBody Category category) {
        categoryService.modifyCategory(category);
        return R.ok();
    }
}

