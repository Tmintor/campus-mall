package com.devcloud.mall.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devcloud.mall.domain.Category;
import com.devcloud.mall.domain.dto.CategoryParentDto;
import com.devcloud.mall.mapper.CategoryMapper;
import com.devcloud.mall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tminto
 * @since 2022-11-01
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryParentDto> getAllCategory() {
        return categoryMapper.selectCategory();
    }


}
