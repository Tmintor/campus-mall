package com.devcloud.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devcloud.mall.domain.Category;
import com.devcloud.mall.domain.dto.CategoryParentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tminto
 * @since 2022-11-01
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryParentDto> selectCategory();


}
