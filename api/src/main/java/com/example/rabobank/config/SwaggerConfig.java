package com.example.rabobank.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket ps4nDocket(TypeResolver typeResolver) {
        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(apiInfo())
                .enable(true)
                .groupName("controller")
                .select()
                .apis(basePackage("com.example.rabobank.controller"))
                .apis(withMethodAnnotation(Swagger.class))
                .build()
                .useDefaultResponseMessages(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("api").description(" Rabobank api description").version("1.0").build();
    }
}
