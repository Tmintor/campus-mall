package com.devcloud.mall.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 吴员外
 * @date 2022/11/2 0:42
 */
@Data
public class CategoryParentDto {

    private String id;

    private String name;

    private List<CategoryChildrenDto> children;

}
