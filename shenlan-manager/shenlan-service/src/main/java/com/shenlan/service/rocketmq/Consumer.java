package com.shenlan.service.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 消费者，装载监听实现后，启动监听。
 */
public class Consumer {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private String consumerGroup;
	private String namesrvAddr;
	private List<String> topics =new ArrayList<String>();
	private String tags;
	private RocketMqMessageListener rocketMqMessageListener;

	public List<String> getTopics() {
		return topics;
	}

	public void setTopics(List<String> topics) {
		this.topics = topics;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getConsumerGroup() {
		return consumerGroup;
	}

	public void setConsumerGroup(String consumerGroup) {
		this.consumerGroup = consumerGroup;
	}

	public String getNamesrvAddr() {
		return namesrvAddr;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}

	public RocketMqMessageListener getRocketMqMessageListener() {
		return rocketMqMessageListener;
	}

	public void setRocketMqMessageListener(RocketMqMessageListener rocketMqMessageListener) {
		this.rocketMqMessageListener = rocketMqMessageListener;
	}

	public void init() {
		consumeMsg(topics);
	}
	
	public void consumeMsg(List<String> topics){
		logger.debug("启动RocketMq监听...{}", this);
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
		consumer.setConsumerGroup(consumerGroup);
		consumer.setNamesrvAddr(namesrvAddr);
		consumer.setMessageModel(MessageModel.BROADCASTING);
		try {
			// 订阅ZXWTopic下Tag为zxwTag的消息
			for(String topic:topics){
				consumer.subscribe(topic, "*");
			}
			// 程序第一次启动从消息队列头取数据
			consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
			RocketMqMessageWrapper rocketMqMessageWrapper = new RocketMqMessageWrapper();
			if (this.rocketMqMessageListener == null) {
				throw new RuntimeException("please define a rocketMqMessageListener for consumer!");
			}
			rocketMqMessageWrapper.setRocketMqMessageListener(this.rocketMqMessageListener);
			consumer.registerMessageListener(rocketMqMessageWrapper);
			consumer.start();

			logger.debug("启动RocketMq监听成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Consumer{" + "topic='" + topics.toString() + '\'' + ", tags='" + tags + '\'' + ", consumerGroup='" + consumerGroup
				+ '\'' + ", namesrvAddr='" + namesrvAddr + '\'' + ", rocketMqMessageListener=" + rocketMqMessageListener
				+ '}';
	}
}