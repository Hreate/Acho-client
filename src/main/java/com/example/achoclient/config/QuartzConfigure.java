package com.example.achoclient.config;

import com.example.achoclient.job.FetchJob;
import com.example.achoclient.job.HeartJob;
import com.example.achoclient.listener.HeartTriggerListener;
import com.example.achoclient.properties.ServiceInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * Quartz的配置类
 */
@Configuration
public class QuartzConfigure {
    @Autowired
    private ServiceInfoProperties serviceInfoProperties;

    /**
     * 创建Job对象
     */
    @Bean(name = "heartJobDetail")
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        //关联自定义的Job类
        factoryBean.setJobClass(HeartJob.class);
        return factoryBean;
    }

    @Bean(name = "fetchJobDetail")
    public JobDetailFactoryBean fetchDetailFactoryBean() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        //关联自定义的Job类
        factoryBean.setJobClass(FetchJob.class);
        return factoryBean;
    }

    /**
     * 创建简单Trigger对象
     */
    @Bean(name = "heartTrigger")
    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(@Qualifier("heartJobDetail") JobDetailFactoryBean heartJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        //关联JobDetail对象
        factoryBean.setJobDetail(heartJobDetail.getObject());
        //该参数表示一个执行的毫秒数
        factoryBean.setRepeatInterval(serviceInfoProperties.getRenewTime() * 1000);
        return factoryBean;
    }

    @Bean(name = "fetchTrigger")
    public SimpleTriggerFactoryBean fetchTriggerFactoryBean(@Qualifier("fetchJobDetail") JobDetailFactoryBean fetchJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        //关联JobDetail对象
        factoryBean.setJobDetail(fetchJobDetail.getObject());
        //该参数表示一个执行的毫秒数
        factoryBean.setRepeatInterval(serviceInfoProperties.getFetchTime() * 1000);
        return factoryBean;
    }

    /**
     * 创建Scheduler对象
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("heartTrigger") SimpleTriggerFactoryBean heartTrigger, @Qualifier("fetchTrigger") SimpleTriggerFactoryBean fetchTrigger) {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        //关联Trigger
        factoryBean.setTriggers(heartTrigger.getObject(), fetchTrigger.getObject());
        //调度器关联心跳监听器
        factoryBean.setGlobalTriggerListeners(new HeartTriggerListener("HeartTriggerListener"));
        //调度器关联Spring上下文
        factoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        return factoryBean;
    }
}
