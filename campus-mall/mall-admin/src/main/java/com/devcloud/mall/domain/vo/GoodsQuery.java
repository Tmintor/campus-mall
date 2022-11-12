package com.devcloud.mall.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 吴员外
 * @date 2022/11/10 21:08
 */
@Data
public class GoodsQuery {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("商品名")
    private String goodsName;

    @ApiModelProperty("商品状态")
    private Integer status;

}
