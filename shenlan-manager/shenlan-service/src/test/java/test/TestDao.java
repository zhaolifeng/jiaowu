package test;
//package test;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.fastjson.JSON;
//import com.common.utils.page.PageParameter;
//import com.common.utils.page.PageSort;
//import com.dao.user.UserDao;
//import com.model.QueryEntity;
//import com.model.user.User;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
//public class TestDao {
//	//service层调用dao
//	protected Logger	logger	= LoggerFactory.getLogger(getClass());
//	
//	@Autowired
//	private UserDao userDao;
//	
//	@Test
//	public void selectById() {
//		User u = userDao.selectById(Integer.valueOf(1).longValue());
//		logger.info(JSON.toJSONString(u));
//	}
//	
//	@Test
//	public void save()
//	{
//		User u = new User();
//		u.setNote("00");
//		u.setUsername("山楂");
//		u.setPassword("123");
//		userDao.save(u);
//	}
//	
//	@Test
//	public void delete()
//	{
//		userDao.delete(Integer.valueOf(1).longValue(),Integer.valueOf(12).longValue());
//	}
//	
//	@Test
//	public void update()
//	{
//		User u = userDao.selectById(Integer.valueOf(3).longValue());
//		u.setNote("update");
//		u.setPassword("update");
//		userDao.update(u);
//	}
//	
//
//	@Test
//	public void batchSave()//有ID则修改，无ID则新增
//	{
//		List<User> ul = new ArrayList<User>();
//		User u = new User();
//		u.setNote("00");
//		u.setUsername("山楂");
//		u.setPassword("123");
//		ul.add(u);
//		User us = userDao.selectById(Integer.valueOf(4).longValue());
//		us.setPassword("update");
//		ul.add(us);
//		userDao.batchSave(ul);
//	}
//	
//	@Test 
//	public void batchSaveByName()//有ID则修改，无ID则新增  指定方法名
//	{
//		List<User> ul = new ArrayList<User>();
//		User u = new User();
//		u.setNote("00");
//		u.setUsername("山楂1");
//		u.setPassword("123");
//		ul.add(u);
//		User us = new User();
//		us.setNote("00");
//		us.setUsername("山楂2");
//		us.setPassword("123");
//		ul.add(us);
//		userDao.batchSave("batchAddByNewName","",ul);
//	}
//	
//	@Test
//	public void loadData()
//	{
//		//start = 0 limit = 5 
//		PageParameter page = new PageParameter();
//		page.setPage(1);
//		page.setLimit(6);
//		PageSort sortPage  = new PageSort();
//		page.setSort(sortPage);
//	
//		User u = userDao.selectById(Integer.valueOf(14).longValue());
//		
//		QueryEntity qe = new QueryEntity("123",u);
//		List<User> list = userDao.loadData(page,qe);
//		logger.info(JSON.toJSONString(list));
//	}
//	
//	@Test
//	public void queryForPage()
//	{
//		User u = userDao.selectById(Integer.valueOf(5).longValue());
//		PageParameter page = new PageParameter();
//		page.setPage(0);
//		page.setLimit(3);
//		PageSort sortPage  = new PageSort();
//		page.setSort(sortPage);
//		page = userDao.queryForPage(u, page);
//		logger.info(JSON.toJSONString(page));
//	}
//}
//

