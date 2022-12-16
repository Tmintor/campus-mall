package com.devcloud.mall.domain.dto;

import javafx.scene.layout.BackgroundImage;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 吴员外
 * @date 2022/12/15 22:35
 * 订单列表类
 */
@Data
public class OrdersDto {

    private String id;
    private String goodsName;
    private BigDecimal totalAmount;
    private Integer status;
    private Date createTime;
    private Date updateTime;

}
