package com.asml.interview.client;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.netflix.ribbon.StaticServerList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class RibbonConfig {
    @LocalServerPort
    int port;

    @Bean
    public ServerList<Server> serverList() {
        return new StaticServerList<>(new Server("localhost", port));
    }
}
