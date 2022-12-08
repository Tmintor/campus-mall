package com.devcloud.mall.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 吴员外
 * @date 2022/12/6 23:17
 *  展示我卖出的和我买到的
 */
@Data
public class OrderListDto {

    private String id;

    private String goodsId;

    private String goodsName;

    private String goodsImage;

    private String sellerId;

    private String sellerName;

    private String sellerAvatar;

    private String status;

    private BigDecimal money;

}
