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
//import Consumer;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml" })
//public class TestConsumer {
//	protected Logger logger = LoggerFactory.getLogger(getClass());
//
//	@Autowired
//	private Consumer rocketMqConsumer;
//
//	@Test
//	public void testProducer() {
//		rocketMqConsumer.consumeMsg("ZXWTopic", "zxwTag");
//	}
//
//}
