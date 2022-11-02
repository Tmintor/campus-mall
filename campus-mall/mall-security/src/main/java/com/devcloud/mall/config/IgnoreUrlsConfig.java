package com.devcloud.mall.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringSecurity白名单资源路径配置
 */
@Data
@ConfigurationProperties(prefix = "secure.ignored")
@Component
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

}
