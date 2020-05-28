package com.example.achoclient.properties;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 绑定配置信息
 */

@Component
public class ServiceInfoProperties {

    @Value("${acho.instance.application.name}")
    private String name;

    @Value("${acho.client.host}")
    private String host;

    @Value("${acho.client.port}")
    private String port;

    @Value("${acho.client.fetch-interval-seconds:30}")
    private Long fetchTime;

    @Value("${acho.client.heart-interval-seconds:30}")
    private Long renewTime;

    @Value("${acho.client.deadline-seconds:90}")
    private Long aliveTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Long getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(Long renewTime) {
        this.renewTime = renewTime;
    }

    public Long getAliveTime() {
        return aliveTime;
    }

    public void setAliveTime(Long aliveTime) {
        this.aliveTime = aliveTime;
    }

    public Long getFetchTime() {
        return fetchTime;
    }

    public void setFetchTime(Long fetchTime) {
        this.fetchTime = fetchTime;
    }
}
