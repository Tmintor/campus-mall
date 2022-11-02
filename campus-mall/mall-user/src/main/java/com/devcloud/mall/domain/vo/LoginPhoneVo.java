package com.devcloud.mall.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 吴员外
 * @date 2022/10/30 23:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LoginPhoneVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电话号")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    private String code;

}
