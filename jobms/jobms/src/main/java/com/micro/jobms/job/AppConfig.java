package com.micro.jobms.job;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean //not needed using Feign clients
    @LoadBalanced //not needed using Feign clients
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
