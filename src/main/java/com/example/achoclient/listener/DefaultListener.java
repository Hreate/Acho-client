package com.example.achoclient.listener;

import com.alibaba.fastjson.JSON;
import com.example.achoclient.pojo.Result;
import com.example.achoclient.pojo.ServerInfo;
import com.example.achoclient.pojo.ServiceInfo;
import com.example.achoclient.properties.ServerInfoProperties;
import com.example.achoclient.properties.ServiceInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 工程启动完成调用的监听器，实现注册，拉取
 */
@Component
public class DefaultListener implements CommandLineRunner {
    private ServerInfoProperties serverInfoProperties;
    private ServiceInfoProperties serviceInfoProperties;
    private RestTemplate restTemplate;
    private Result result;
    private HashMap<String, ServiceInfo[]> serviceMap;

    @Autowired
    private DefaultListener(ServerInfoProperties serverInfoProperties, ServiceInfoProperties serviceInfoProperties, RestTemplate restTemplate, Result result,@Qualifier("serviceMap") HashMap<String, ServiceInfo[]> serviceMap) {
        this.serverInfoProperties = serverInfoProperties;
        this.serviceInfoProperties = serviceInfoProperties;
        this.restTemplate = restTemplate;
        this.result = result;
        this.serviceMap = serviceMap;
    }
    @Override
    public void run(String... args) {
        registry();
        //如果注册成功，则拉取全部服务信息
        if (result.getStatus() == false) {
            fetch();
        }
    }

    private void registry() {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setHost(serviceInfoProperties.getHost());
        serviceInfo.setPort(serviceInfoProperties.getPort());
        serviceInfo.setName(serviceInfoProperties.getName());
        serviceInfo.setRenewTime(serviceInfoProperties.getRenewTime());
        serviceInfo.setAliveTime(serviceInfoProperties.getAliveTime());
        String infoJson = JSON.toJSONString(serviceInfo);
        Result r = restTemplate.postForObject(serverInfoProperties.serverUrl + "/registry", infoJson, Result.class);
        result.setStatus(r.getStatus());
    }

    private void fetch() {
        String servicesInfo = restTemplate.getForObject(serverInfoProperties.serverUrl + "/fetch", String.class);
        serviceMap = JSON.parseObject(servicesInfo, HashMap.class);
        System.out.println(serviceMap);
        //拉取完后还未处理
    }
}
