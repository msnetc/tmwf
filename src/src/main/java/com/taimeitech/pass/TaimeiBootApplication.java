package com.taimeitech.pass;

import com.taimeitech.framework.common.TaimeiRestTemplate;
import javafx.application.Application;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.test.Deployment;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/*
如果不需要Swagger模块，则添加如下开关
@SpringBootApplication(exclude = {SwaggerAutoConfiguration.class})
*/

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.taimeitech.pass.mapper")
public class TaimeiBootApplication   {

    public static void main(String[] args) {
        SpringApplication.run(TaimeiBootApplication.class, args);
    }

    //定义RestTemplate实例并开启负载均衡
    @Bean
    @LoadBalanced
    TaimeiRestTemplate restTemplate() {
        return new TaimeiRestTemplate();
    }

}

