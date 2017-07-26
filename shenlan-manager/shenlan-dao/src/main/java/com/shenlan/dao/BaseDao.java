package com.shenlan.dao;

import com.shenlan.common.utils.Com;
import com.shenlan.common.utils.page.PageBoundsProxy;
import com.shenlan.common.utils.page.PageParameter;
import com.shenlan.domain.BaseEntity;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;





@SuppressWarnings("all")
public class BaseDao<T> extends SqlSessionDaoSupport
{

	protected String			namespace;

	@Resource(name = "sqlSessionFactory")
	protected SqlSessionFactory	sqlSessionFactory;

	@Autowired
	protected JdbcTemplate		jdbcTemplate;

	protected Integer			BATCH_LIMIT	= 50;

	public BaseDao()
	{
		namespace = this.getClass().getName();
	}

	public void setNamespace(String namespace)
	{
		this.namespace = namespace;
	}

	@PostConstruct
	public void sqlSessionFactory()
	{
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory)
	{
		this.sqlSessionFactory = sqlSessionFactory;
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public JdbcTemplate getJdbcTemplate()
	{
		return jdbcTemplate;
	}

	public int batchSave(String mo, List<? extends BaseEntity> list)
	{
		SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int i = 0;
		for (int cnt = list.size(); i < cnt; i++)
		{
			BaseEntity entity = list.get(i);
			batchSession.update(this.namespace + "." + mo, entity);

			if ((i + 1) % BATCH_LIMIT == 0)
			{
				batchSession.flushStatements();
			}
		}
		batchSession.flushStatements();
		batchSession.close();
		return i;
	}

	public int batchSave(List<? extends BaseEntity> list)
	{
		SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int i = 0;
		for (int cnt = list.size(); i < cnt; i++)
		{
			BaseEntity entity = list.get(i);
			if (Com.hv(entity.getId()))
			{
				batchSession.update(this.namespace + ".batchUpdate", entity);
			}
			else
			{
				batchSession.insert(this.namespace + ".batchAdd", entity);
			}

			if ((i + 1) % BATCH_LIMIT == 0)
			{
				batchSession.flushStatements();
			}
		}
		batchSession.flushStatements();
		batchSession.close();
		return i;
	}

	public int batchSave(String save, String update, List<? extends BaseEntity> list)
	{
		SqlSession batchSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		int i = 0;
		for (int cnt = list.size(); i < cnt; i++)
		{
			BaseEntity entity = list.get(i);

			if (Com.hv(entity.getId()))
			{
				batchSession.update(this.namespace + "." + update, entity);
			}
			else
			{
				batchSession.insert(this.namespace + "." + save, entity);
			}

			if ((i + 1) % BATCH_LIMIT == 0)
			{
				batchSession.flushStatements();
			}
		}
		batchSession.flushStatements();
		batchSession.close();
		return i;
	}

	/**
	 * 查询列表
	 * 
	 * @param t
	 * @return
	 */
	public PageParameter queryForPage(T t,PageParameter page)
	{
		PageBoundsProxy pbp = new PageBoundsProxy(page);
		List<T> list = this.getSqlSession().selectList(namespace + ".queryForPage", t,pbp.getPageBounds());
		pbp.setPageParameter(list);
//		page.setData(list);
//		page.setTotalCount(pbp.getTotalCount(list));
//		page.setTotalPage(pbp.getTotalPage(pbp.getTotalCount(list)));
		return page;
	}

	/**
	 * 根据ID获取实体
	 * 
	 * @param id
	 * @return
	 */
	public T selectById(Long id)
	{
		return (T) this.getSqlSession().selectOne(namespace + ".selectById", id);
	}

	/**
	 * 根据ID获取实体
	 *
	 * @param id
	 * @return
	 */
	public T selectById(String id)
	{
		return (T) this.getSqlSession().selectOne(namespace + ".selectById", id);
	}

	/**
	 * 保存
	 * @param t
	 * @return
	 */
	public void save(T t)
	{
		this.getSqlSession().insert(namespace + ".save", t);
	}

	/**
	 * 更新
	 * 
	 * @param t
	 * @return
	 */
	public void update(T t)
	{
		this.getSqlSession().update(namespace + ".update", t);
	}

	/**
	 * 根据ID删除
	 * 
	 * @param id
	 */
	public void delete(Long... ids)
	{
		this.getSqlSession().update(namespace + ".delete", ids);
	}
}
