//package test;
//
//import com.alibaba.fastjson.JSON;
//import SystemUser;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPool;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml"})
//public class TestService {
//
//	protected Logger	logger	= LoggerFactory.getLogger(getClass());
//
//	@Autowired
//	private IUserService userService;
//	@Autowired
//	private RedisTemplate redisTemplate;
//	@Autowired
//	private JedisPool jedisPool;
////	@Autowired
////	private JedisClient jedisClient;
//	@Autowired
//	private JedisCluster jedisCluster;
//
////	@Test
////	public void selectById() {
////		User u = userService.getObjectById(Integer.valueOf(2).longValue());
////		logger.info(JSON.toJSONString(u));
////	}
////
////	@Test
////	public void save()
////	{
//////		User u = new User();
//////		u.setNote("00");
//////		u.setUsername("山楂111");
//////		u.setPassword("123");
//////		userService.save(u);
////	}
////
////	@Test
////	public void delete()
////	{
//////		userService.delete(Integer.valueOf(1).longValue(),Integer.valueOf(12).longValue());
////	}
////
////	@Test
////	public void update()
////	{
//////		User u = userService.getObjectById(Integer.valueOf(3).longValue());
//////		u.setNote("update");
//////		u.setPassword("update");
//////		userService.update(u);
////	}
////	@Test
////	public void getRedisValue(){
//////		redisTemplate.opsForValue().set("a","东航项目");
//////		String s= (String) redisTemplate.opsForValue().get("a");
//////		logger.info("---------"+s);
////	}
//
//	@Test
//	public void testJedis(){
//		Jedis jedis=jedisPool.getResource();
//		jedis.set("abc".getBytes(),"def".getBytes());
////		logger.info("dddddddddddddddd"+jedis.get("abc"));
//		jedisPool.returnBrokenResource(jedis);
////		String a=jedisClient.getStringFromJedis("abc","df");
////		logger.info("dddddddddddddddd"+a);
//	}
//
//	@Test
//	public void testJedisCluster(){
//		jedisCluster.set("cluster","testclustervalue");
//		String var=jedisCluster.get("cluster");
//		logger.info("----------"+var);
//	}
//
//    @Test
//    public void testJsonChange(){
//		SystemUser SystemUser=new SystemUser();
//		SystemUser.setUsername("zlf");
//		logger.info("---------"+JSON.toJSONString(SystemUser));
//        SystemUser user=JSON.parseObject(JSON.toJSONString(SystemUser), SystemUser.class);
//        logger.info("---------"+user.getUsername());
//    }
//
//}
//
