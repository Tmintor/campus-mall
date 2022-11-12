package com.devcloud.mall.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * @author 吴员外
 * @date 2022/11/4 9:06
 */
public class BeanUtils {

    public static Map<String,Object> beanToPageMap(Page page) {
        Map<String, Object> map = BeanUtil.beanToMap(page, "records", "total", "size", "current");
        map.put("hasNext", page.hasNext());
        map.put("hasPrevious", page.hasPrevious());
        return map;
    }

}
