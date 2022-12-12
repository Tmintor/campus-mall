package com.devcloud.mall.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devcloud.mall.domain.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devcloud.mall.domain.dto.GoodsDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    GoodsDetailDto selectGoodsDetailById(String id);

    IPage<GoodsDetailDto> selectGoodsListPage(IPage<GoodsDetailDto> page);

    void changeGoodsNumber(@Param("goodsId") String goodsId,
                           @Param("number") Integer number,
                           @Param("updateTime") Date updateTime);

    IPage<GoodsDetailDto> selectGoodsDetailByCateParentId(Page<GoodsDetailDto> page, @Param("cid")String cid);
}
