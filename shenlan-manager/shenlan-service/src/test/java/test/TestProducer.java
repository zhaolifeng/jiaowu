//package test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.rocketmq.client.producer.SendResult;
//import com.alibaba.rocketmq.common.message.Message;
//import Producer;
//
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml"})
//public class TestProducer {
//	
//	protected Logger	logger	= LoggerFactory.getLogger(getClass());
//	
//	@Autowired
//    private Producer rocketMqProducer;
//	
//	@Test
//	public void testProducer() {
//		for (int i=0;i<10;i++) {
//            try {
//                Message msg = new Message("ZXWTopic",
//                        "zxwTag",
//                        ""+System.currentTimeMillis(),
//                        "Just for test.".getBytes());
//
//                SendResult result = rocketMqProducer.send(msg);
//                System.out.println("id:" + result.getMsgId() +
//                        " result:" + result.getSendStatus());
//            } catch (Exception e) {
//                e.printStackTrace();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//            }
//		}
//	}
//}

