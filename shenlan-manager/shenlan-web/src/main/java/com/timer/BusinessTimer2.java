package com.timer;

import org.apache.log4j.Logger;

/**
 * Created by 赵利锋 on 2016/7/11.
 */
public class BusinessTimer2 {
    protected static final Logger log= Logger.getLogger(BusinessTimer.class);

    private int timeout;

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
//    @Scheduled(cron = "0/2 * * * * ?")
//    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        log.info("-----定时任务执行-----");
//    }

    public void doIt(){
        log.info("-----定时任务执行-----");

    }
    public void doIt1(){
        log.info("-----定时任务执行-----");

    }


}
