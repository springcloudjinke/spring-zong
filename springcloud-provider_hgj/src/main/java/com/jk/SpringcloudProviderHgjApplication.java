package com.jk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient //表明自己是一个eurekaclient.
@RestController
@MapperScan("com.jk.dao")
public class SpringcloudProviderHgjApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudProviderHgjApplication.class, args);
    }

}
