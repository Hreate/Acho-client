package com.example.achoclient.config;

import com.example.achoclient.pojo.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultConfigure {
    @Bean
    public Result result() {
        return new Result();
    }
}
