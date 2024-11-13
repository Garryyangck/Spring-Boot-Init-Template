package com.garry.springbootinittemplate.batch.job;


import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@DisallowConcurrentExecution // 禁止任务并发执行
public class QuartzTestJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        try {
            log.info("QuartzTestJob is sleeping...");
            log.info("QuartzTestJob is sleeping...");
            log.info("QuartzTestJob is sleeping...");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException("被唤醒了");
        }
    }
}
