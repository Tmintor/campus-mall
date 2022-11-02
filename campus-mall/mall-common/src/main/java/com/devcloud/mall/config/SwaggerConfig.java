package com.devcloud.mall.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 吴员外
 * @date 2022/9/17 12:49
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("DevCloud")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .paths(Predicates.not(PathSelectors.regex("/actuator/health.*")))
                .paths(Predicates.not(PathSelectors.regex("/actuator")))
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("校园闲置物品交易系统")
                .description("")
                .version("1.0")
                .contact(new Contact("Java", "http://www.github.com", "1437345514@qq.com"))
                .build();
    }

}
