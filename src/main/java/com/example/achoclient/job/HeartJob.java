package com.example.achoclient.job;

import com.alibaba.fastjson.JSON;
import com.example.achoclient.pojo.HeartInfo;
import com.example.achoclient.properties.ServerInfoProperties;
import com.example.achoclient.properties.ServiceInfoProperties;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 心跳定时任务
 */

public class HeartJob implements Job {
    private Logger logger = LoggerFactory.getLogger(HeartJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("心跳已发送");
        try {
            ApplicationContext applicationContext = (ApplicationContext)jobExecutionContext.getScheduler().getContext().get("applicationContext");
            ServiceInfoProperties serviceInfoProperties = applicationContext.getBean(ServiceInfoProperties.class);
            HeartInfo heartInfo = new HeartInfo();
            heartInfo.setHost(serviceInfoProperties.getHost());
            heartInfo.setPort(serviceInfoProperties.getPort());
            heartInfo.setName(serviceInfoProperties.getName());
            heartInfo.setStatus("up");
            String infoJson = JSON.toJSONString(heartInfo);
//            System.out.println(infoJson);
            ServerInfoProperties serverInfoProperties = applicationContext.getBean(ServerInfoProperties.class);
            RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);
            restTemplate.put(serverInfoProperties.serverUrl + "/heart", infoJson);
            logger.info("get response statusCode: 200");
        } catch (SchedulerException | RestClientException e) {
            e.printStackTrace();
        }
    }
}
