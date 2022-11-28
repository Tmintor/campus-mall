package com.devcloud.mall.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devcloud.mall.domain.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devcloud.mall.domain.dto.GoodsDetailDto;
import org.apache.ibatis.annotations.Param;

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

    IPage<GoodsDetailDto> selectGoodsListPage(IPage<GoodsDetailDto> page);

    void incrGoodsNumber(@Param("goodsId") String goodsId, @Param("number")Integer number);
}
