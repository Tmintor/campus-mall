package com.devcloud.mall.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 吴员外
 * @date 2022/11/26 15:26
 */
@Data
public class ShopcarDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Shopcar shopcar;

    private String goodsName;

    /*库存*/
    private Integer amount;

    private BigDecimal price;

}
