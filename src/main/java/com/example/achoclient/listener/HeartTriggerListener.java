package com.example.achoclient.listener;

import com.example.achoclient.pojo.Result;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.springframework.context.ApplicationContext;

/**
 * 触发心跳的监听器
 * 收到注册成功的响应才会开启心跳
 */
public class HeartTriggerListener implements TriggerListener {

    private String name;

    public HeartTriggerListener(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        try {
            ApplicationContext applicationContext =(ApplicationContext) jobExecutionContext.getScheduler().getContext().get("applicationContext");
            Result result = applicationContext.getBean(Result.class);
            if (result.getStatus() == true) {
                return true;
            } else {
                return false;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {

    }
}
