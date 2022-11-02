package com.devcloud.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 吴员外
 * @date 2022/10/28 0:34
 */
@SpringBootApplication
@MapperScan("com.devcloud.mall.mapper")
public class MallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
