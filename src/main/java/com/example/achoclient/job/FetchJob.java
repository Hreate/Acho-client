package com.example.achoclient.job;

import com.alibaba.fastjson.JSON;
import com.example.achoclient.pojo.ServiceInfo;
import com.example.achoclient.properties.ServerInfoProperties;
import com.example.achoclient.properties.ServiceInfoProperties;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * 定时拉取所有服务信息任务
 */
public class FetchJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            ApplicationContext applicationContext = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("applicationContext");
            RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);
            ServerInfoProperties serverInfoProperties = applicationContext.getBean(ServerInfoProperties.class);
            HashMap<String, ServiceInfo[]> map = (HashMap<String, ServiceInfo[]>) applicationContext.getBean("serviceMap");
            String servicesInfo = restTemplate.getForObject(serverInfoProperties.serverUrl + "/fetch", String.class);
            map = JSON.parseObject(servicesInfo, HashMap.class);
            System.out.println("定时拉取的服务为：" + map);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
