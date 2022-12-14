package com.devcloud.mall.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吴员外
 * @date 2022/11/2 1:03
 */
@Data
public class CategoryChildrenDto {

    private String id;

    private String pid;

    private String name;

    private String icon;


}
