package com.squareshift.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Configuration
//@EnableSwagger2WebMvc
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi2() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.squareshift.ecommerce.controller"))
                .paths(PathSelectors.regex("/.*")).build().apiInfo(metaData()).groupName("external");

    }
    private ApiInfo metaData() {
        @SuppressWarnings("deprecation") ApiInfo
                apiInfo = new ApiInfo("Squareshift ecommerce", "Rest APIs for cart items", "1.0",
                "", "pradeep Kushwah", "",
                "kushwahpradeep91@gmail.com");
        return apiInfo;
    }
}
