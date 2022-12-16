package com.devcloud.mall.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 吴员外
 * @date 2022/12/15 22:54
 * 订单详细
 */
@Data
public class OrderDetailDto {

    private String id;
    private String sellerId;
    private String sellerName;
    private String goodsId;
    private String goodsName;
    private BigDecimal goodsPrice;//商品单价
    private String customerId;
    private String customerName;
    private Integer status;
    private String number;
    private BigDecimal totalAmount;//订单金额
    private String remark;//订单备注
    private String address;//收货地址
    private String phone;//收货人号码
    private Date createTime;
    private Date updateTime;

}
