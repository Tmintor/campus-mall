package com.devcloud.mall.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 吴员外
 * @date 2022/11/25 21:24
 */
@Setter
@Getter
public class AvatarVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String avatar;

}
