package com.shenlan.service.rocketmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * 生产者，初始化MQProducer
 */
public class Producer {
	private Logger logger= LoggerFactory.getLogger(getClass());
	
    private String producerGroup;
    private String namesrvAddr;
    private DefaultMQProducer producer;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public void init(){
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     */
    public SendResult send(Message message) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        SendResult result=null;
        try {
            producer.setRetryAnotherBrokerWhenNotStoreOK(true);
            producer.setRetryTimesWhenSendFailed(3);
            result = producer.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
//            shutdown();
        }
        return result;
    }

    public void destory(){
        shutdown();
    }
    
    /**
     * 关闭生产者
     */
    public void shutdown()
    {
    	if(producer != null)
    	{
    		producer.shutdown();
    	}
    }
}