package com.devcloud.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devcloud.mall.domain.Goods;
import com.devcloud.mall.domain.dto.CategoryParentDto;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    List<CategoryParentDto> selectCategory();
}
