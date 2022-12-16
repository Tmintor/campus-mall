package com.devcloud.mall.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devcloud.mall.domain.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devcloud.mall.domain.dto.MySoldDto;
import com.devcloud.mall.domain.dto.OrderDetailDto;
import com.devcloud.mall.domain.dto.MyBuyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

    OrderDetailDto selectOrderDetail(String orderId);

    Page<MySoldDto> selectMySold(Page<MyBuyDto> page, @Param("userId") String userId);

    Page<MyBuyDto> selectMyBuy(Page<MyBuyDto> page, String userId);
}
