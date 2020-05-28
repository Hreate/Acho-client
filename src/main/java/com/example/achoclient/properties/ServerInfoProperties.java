package com.example.achoclient.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerInfoProperties {
    @Value("${acho.client.server-url}")
    public String serverUrl;
}
