package com.devcloud.mall.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author 吴员外
 * @date 2022/11/26 14:13
 */
@ApiModel("生成订单时接收各个商品Vo")
@Data
public class GoodsVo {

    private String goodsId;

    private Integer number;

}
