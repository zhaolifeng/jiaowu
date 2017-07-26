package test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.JedisCluster;




/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml"})
public class TestSystemService {
	
	protected Logger	logger	= LoggerFactory.getLogger(getClass());

	@Autowired
	private SystemUserService systemUserService;
//	
//	@Autowired
//	private SystemRoleService systemRoleService;
//	
	@Autowired
	private SystemMenuService systemMenuService;
	
	@Autowired
	private SystemRoleUserDao sss;
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Test
	public void test3() {
//		List<SystemRoleUser> list = sss.findAllByUserId(2L);
//		logger.info("----------"+list);
	}
	@Test
	public void test1() {
		SystemUser su = new SystemUser();
		su.setIsValid(1);
		su.setUsername("ad");
		su.setNickname("admin52");
		su.setPassword("admin5");
		su.setUserNo("admin0052");
//		systemUserService.addSystemUser(su);
//		systemUserService.deleteSystemUser(5L);
//		systemUserService.updateSystemUser(su);
	}

	@Test
	public void test2() {
//		SystemUser su = new SystemUser();
//		su.setIsValid(1);
//		su.setUsername("ad");
//		PageParameter p = new PageParameter();
//		p.setPage(1);
//		p.setLimit(5);
//		PageSort sort = new PageSort();
//		p.setSort(sort);
//		
//		PageParameter pp = systemUserService.querySystemUserByName(p, su);
//		logger.info("----------"+pp);
	}
	@Test
	public void testJedisCluster(){
		List<SystemMenu> u = systemMenuService.findRootMenusByUser("admin2");
		
//		jedisCluster.set("cluster",JSON.toJSONString(u));
//		String str = jedisCluster.get("cluster");
//		List<SystemMenu> uu = JSON.parseArray(str, SystemMenu.class);
//		logger.info("----------"+uu);
		System.out.println(u);
	}
	
}*/

