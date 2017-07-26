package com.taimeitech.pass;

import com.taimeitech.framework.common.TaimeiRestTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;

/*
如果不需要Swagger模块，则添加如下开关
@SpringBootApplication(exclude = {SwaggerAutoConfiguration.class})
*/

//TaimeiBootApplication类名需要根据项目修改
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.taimeitech.pass.dao")
public class TaimeiBootApplication {

    ///java -jar /usr/local/workflow/tmwf-1.0-SNAPSHOT.jar
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

