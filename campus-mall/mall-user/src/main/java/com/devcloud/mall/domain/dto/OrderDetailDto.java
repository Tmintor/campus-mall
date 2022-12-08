package com.devcloud.mall.domain.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 吴员外
 * @date 2022/12/6 21:49
 */
@Data
public class OrderDetailDto {

    private String id;

    private String alipayTraceNo;

    private String goodsName;

    private Integer goodsNumber;

    private String goodsImage;

    private BigDecimal totalAmount;

    private String sellerName;

    private String address;

    private String phone;

    private String remark;

    private String status;

    private Date createTime;

    private Date payTime;

}
