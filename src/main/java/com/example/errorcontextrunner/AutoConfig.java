package com.example.errorcontextrunner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class AutoConfig {

    @Bean
    String myString() {
        return "hello";
    }

}
