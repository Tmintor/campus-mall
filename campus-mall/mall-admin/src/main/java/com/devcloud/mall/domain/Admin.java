package com.devcloud.mall.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 吴员外
 * @date 2022/10/27 21:01
 */

@TableName("admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String username;

    private String password;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
