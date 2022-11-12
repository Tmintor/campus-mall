package com.devcloud.mall.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 吴员外
 * @date 2022/11/11 10:22
 */
@Data
@ApiModel("商品详细展示类")
public class GoodsDetailDto {

    @ApiModelProperty(value = "商品id")
    private String id;

    @ApiModelProperty(value = "卖家id")
    private String sellerId;

    @ApiModelProperty(value = "卖家姓名")
    private String sellerName;

    @ApiModelProperty(value = "卖家头像")
    private String avatar;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "商品图片地址")
    private String image;

    @ApiModelProperty(value = "商品数量")
    private Integer number;

    @ApiModelProperty(value = "点击数量")
    private Integer viewCount;

}
