package com.devcloud.mall.domain.dto;

import com.devcloud.mall.domain.GoodsAttributes;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 吴员外
 * @date 2022/12/13 13:14
 */
@Data
public class GoodsPublishDto {

    private String id;

    private String name;

    private String image;

    private Integer number;

    private BigDecimal price;

    private Integer status;

    private List<GoodsAttributes> attributes;



}
