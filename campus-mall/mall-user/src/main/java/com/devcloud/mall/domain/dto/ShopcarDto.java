package com.devcloud.mall.domain.dto;

import lombok.Data;

/**
 * @author 吴员外
 * @date 2022/11/26 17:50
 */
@Data
public class ShopcarDto {

    private String id;

    private String sellerId;

    private String sellerName;

    private String avatar;

    private String goodsId;

    private String goodsName;

    private String goodsImage;

    private String status;

    //private String attribute;

    private double price;

    private Integer number;
}
