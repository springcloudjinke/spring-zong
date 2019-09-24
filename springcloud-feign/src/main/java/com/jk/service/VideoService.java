package com.jk.service;



import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-provider-dhy")  //指定调用哪个服务
public interface VideoService extends VideoServiceApi{



}
