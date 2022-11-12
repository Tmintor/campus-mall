package com.devcloud.mall.mapper;

import com.devcloud.mall.domain.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devcloud.mall.domain.dto.GoodsDetailDto;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    GoodsDetailDto selectGoodsDetailById(String id);
}
