package com.devcloud.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devcloud.mall.domain.Goods;
import com.devcloud.mall.domain.dto.CategoryParentDto;
import com.devcloud.mall.domain.vo.GoodsQuery;
import org.apache.ibatis.annotations.Param;

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

    IPage<Goods> selectPageByQuery(Page<Goods> page, @Param("goodsQuery") GoodsQuery goodsQuery);
}
