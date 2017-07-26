package com.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by 赵利锋 on 2016/7/11.
 */
@Component
public class BusinessTimer{
    /**
     * 启动时执行一次，之后每隔3秒执行一次
     */
    @Scheduled(fixedRate = 1000 * 3)
    public void print() {
        System.out.println("timer running...");
    }

    /**
     * 定时启动。每天凌晨 16:19 执行一次
     */
    @Scheduled(cron = "0 19 16 * * *")
    public void show() {
        System.out.println("定时器启动...");
    }
}
