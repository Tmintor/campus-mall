package com.devcloud.mall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 吴员外
 * @date 2022/12/2 15:47
 */
@Data
public class ConfirmOrder {

    private String id;

    private String shopcarId;

    private String goodsId;

    private String goodsName;

    private String goodsImage;

    private List<GoodsAttributes> attributes;

    private String sellerId;

    private String sellerName;

    private String sellerAvatar;

    private Integer number;

    @JsonIgnore
    private Integer amount;

    private BigDecimal price;

}
