package com.devcloud.mall.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;


/**
 * @author 吴员外
 * @date 2022/11/26 13:50
 */
@Getter
@Setter
@ApiModel("删除购物车商品Vo")
public class ShopcarVo {

    private String userId;

    private String goodsId;

}


