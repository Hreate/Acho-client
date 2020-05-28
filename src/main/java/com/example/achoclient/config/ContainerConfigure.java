package com.example.achoclient.config;

import com.example.achoclient.pojo.ServiceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ContainerConfigure {
    @Bean(name = "serviceMap")
    public HashMap<String, ServiceInfo[]> createMap() {
        return new HashMap<String, ServiceInfo[]>();
    }
}
