package com.jk.service;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-provider-yyw")  //指定调用哪个服务
public interface UserService extends UserServiceApi {

}
