package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient //表明自己是一个eurekaclient.
@RestController
public class SpringcloudProviderLbwApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudProviderLbwApplication.class, args);
    }

}
