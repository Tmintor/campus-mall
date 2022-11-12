package com.devcloud.mall.config;

import com.baomidou.mybatisplus.core.toolkit.Sequence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 吴员外
 * @date 2022/11/3 8:36
 */
@Configuration
public class IDGenerator {

    @Bean
    public Sequence sequence() {
        return new Sequence();
    }

}
