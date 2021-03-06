package com.ndamelio.learning.serviceconsumer.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="zuul-api-gateway")
//@FeignClient(name ="microservice-a")
@RibbonClient(name="microservice-a")
public interface RandomServiceProxy {

    @GetMapping(value="/microservice-a/random")
    public List<Integer> getRandomNumbers();
}