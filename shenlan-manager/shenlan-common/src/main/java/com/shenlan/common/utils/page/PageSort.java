package com.shenlan.common.utils.page;

import java.io.Serializable;

/**
 * 排序实体
 */
public class PageSort implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	/**
	 * 排序方式
	 * asc 升
	 * desc 降
	 */
	private String				sort;

	/**
	 * 排序列
	 */
	private String				dir;
    
	public PageSort()
	{
		super();
	}

	/**
	 * 
	 * @param dir 列名
	 * @param sort asc 或者 desc
	 */
	public PageSort(String dir,String sort)
	{
		super();
		this.sort = sort;
		this.dir = dir;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		this.sort = sort;
	}

	public String getDir()
	{
		return dir;
	}

	public void setDir(String dir)
	{
		this.dir = dir;
	}

}
