package com.devcloud.mall.domain.vo;

import lombok.Data;

/**
 * @author 吴员外
 * @date 2022/12/15 22:28
 *  订单检索字段
 */
@Data
public class OrderQueryVo {

    private String sellerName;

    private String customerName;

    private String goodsName;

}
