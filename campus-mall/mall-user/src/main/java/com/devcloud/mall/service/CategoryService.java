package com.devcloud.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.devcloud.mall.domain.Category;
import com.devcloud.mall.domain.dto.CategoryParentDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tminto
 * @since 2022-11-01
 */
public interface CategoryService extends IService<Category> {

    List<CategoryParentDto> getAllCategory();

}
