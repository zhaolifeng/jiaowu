package test;
//package test;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
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
//import com.common.utils.page.PageBoundsProxy;
//import com.common.utils.page.PageSort;
//import com.dao.UserDao;
//import com.github.miemiedev.mybatis.paginator.domain.Order;
//import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
//import com.github.miemiedev.mybatis.paginator.domain.PageList;
//import com.model.user.User;
//import com.service.user.UserServicePage;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
//public class TestPage {
//	
//	protected Logger	logger	= LoggerFactory.getLogger(getClass());
//	
//	@Autowired
//	private UserServicePage userService;
//	
//	@Autowired
//	private UserDao userDao;
//	
//	@Test
//	public void testPage() {
//		Map<String, Object> params = new HashMap<String, Object>();
//	    params.put("password","123");
////	    List list = userService.selectAllByPage(params, new PageBoundsProxy().getPageList());
////	    List list = userService.selectAllByPage(params, new PageBoundsProxy(2,3).getPageList());
//	    
//	    List<PageSort> listps = new ArrayList<PageSort>();
//	    listps.add(new PageSort("note","desc"));
//	    PageBoundsProxy pro = new PageBoundsProxy(1,3,listps);
//	    
//	    List list = userService.selectAllByPage(params, pro.getPageList());
//		System.out.println("totalCount: " +pro.getTotalCount(list));
//		System.out.println("totalPage: " +pro.getTotalPage(pro.getTotalCount(list)));
//		logger.info(JSON.toJSONString(list));
//	}
//}
//

