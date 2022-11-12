package com.devcloud.mall.domain.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 吴员外
 * @date 2022/11/11 9:09
 */
@Data
public class GoodsDto {

    @ApiModelProperty(value = "商品id")
    private String id;

    @ApiModelProperty(value = "卖家id")
    private String userId;

    @ApiModelProperty(value = "卖家名")
    private String username;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "状态")
    private Integer status;

}
