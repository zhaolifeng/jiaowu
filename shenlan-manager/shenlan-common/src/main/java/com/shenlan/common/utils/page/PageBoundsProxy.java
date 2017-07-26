package com.shenlan.common.utils.page;

import java.io.Serializable;
import java.util.List;

import com.shenlan.common.utils.Com;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 分页代理类
 */
public class PageBoundsProxy implements Serializable
{

	private static final long	serialVersionUID	= 1L;
	
	private PageParameter	pp;
	
	/** 页码 */
	private int		page;

	/** 每页记录数 */
	private int		pageSize;
	
	private PageSort sortPage  = new PageSort();

	public PageBoundsProxy(PageParameter page)
	{
		this.pp = page;
		this.page = page.getPage();
		this.pageSize = page.getLimit();
		this.sortPage = page.getSort();
		
	}
	/**获取PageBounds对象*/
	public PageBounds getPageBounds()
	{
		String str = null;
		if(Com.hv(sortPage.getDir()))
		{
			str = sortPage.getDir()+"."+sortPage.getSort();
		}
		return new PageBounds(page, pageSize, Order.formString(str));
	}
	/**获取满足查询条件的总记录数*/
	public int getTotalCount(List<?> list)
	{
		PageList<?> pageList = null;
		if(list instanceof PageList<?>)
		{
			pageList = (PageList<?>)list;
		}
		return pageList.getPaginator().getTotalCount();
	}
	/**获取满足查询条件的总页数*/
	public int getTotalPage(int totalCount)
	{
		return (totalCount+pageSize - 1)/pageSize;
	}
	/**
	 * list 数据源
	 */
	public void setPageParameter(List<?> list) {
		this.pp.setData(list);
		if(list != null && list.size() != 0)
		{
			this.pp.setTotalCount(this.getTotalCount(list));
			this.pp.setTotalPage(this.getTotalPage(this.getTotalCount(list)));
		}
		
	}
	
	public PageParameter getPageParameter()
	{
		return this.pp;
	}
}
