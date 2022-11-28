package com.devcloud.mall.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Goods对象")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "卖家id")
    private String userId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品描述")
    private String description;

    /*@ApiModelProperty(value = "商家自定义的商品属性")
    private String attributes;*/

    @ApiModelProperty(value = "商品图片地址")
    private String image;

    @ApiModelProperty(value = "商品数量")
    private Integer number;

    @ApiModelProperty(value = "二级分类id")
    private String cateId;

    @ApiModelProperty(value = "一级分类id")
    private String cateParentId;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "点击数量")
    private Integer viewCount;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



}
