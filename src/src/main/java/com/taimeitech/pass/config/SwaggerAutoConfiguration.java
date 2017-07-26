package com.taimeitech.pass.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zhengjun.zhang on 2017/6/22.
 */
@Configuration
@EnableSwagger2
public class SwaggerAutoConfiguration {
    @Value(value="${spring.application.name:Swagger API list}")
    private String title;

    @Value(value="${spring.application.description:This is to show all swagger controller API description}")
    private String description;

    @Value(value="${spring.application.version:1.0.0}")
    private String version;

    @Value(value="${spring.application.contact:zhengjun.zhang@mobilemd.cn}")
    private String contact;

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version(version)
                .contact(new Contact("", "", contact))
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.taimeitech.pass.api.workflow"))
                .paths(PathSelectors.any())
                .build();
    }
}