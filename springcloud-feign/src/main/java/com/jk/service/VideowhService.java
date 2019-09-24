package com.jk.service;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "SERVICE-PROVIDER")
public interface VideowhService extends VideowhServiceApi {

}
