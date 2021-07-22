package com.asml.interview.client;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = TemperatureClient.class)
@EnableAutoConfiguration
@RibbonClient(
        name = "TemperatureProvider",
        configuration = RibbonConfig.class)
public class FeignConfig {
}
