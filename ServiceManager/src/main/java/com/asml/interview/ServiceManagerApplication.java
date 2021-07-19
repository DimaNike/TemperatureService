package com.asml.interview;

@SpringBootApplication
@EnableEurekaServer
public class ServiceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
