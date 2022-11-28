package com.devcloud.mall.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author 吴员外
 * @date 2022/11/12 10:46
 */
@Data
@ApiModel("用户自定义属性")
public class GoodsAttributes {

    private String id;

    private String key;

    private String value;
}
