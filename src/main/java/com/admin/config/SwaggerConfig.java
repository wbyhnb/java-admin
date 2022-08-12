package com.weione.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    //swagger3配置
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("wei-api")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.weione.controller")) // 接口扫描方案
                .paths(PathSelectors.any())
                .build();
        //.pathMapping("/wei-api"); // 设置访问路径前缀


    }

}
