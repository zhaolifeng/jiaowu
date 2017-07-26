package com.shenlan.service.startup;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 赵利锋 on 2016/4/22.
 */
public class Provider {
        public static void main(String[] args)throws Exception{
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext(
        		new String[] {"spring.xml"});
        context.start();
        System.in.read();// 按任意键退出
        }
        }
