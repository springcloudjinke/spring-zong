package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-provider-hgj")  //指定调用哪个服务
public interface LoveService extends LoveServiceApi {
}
