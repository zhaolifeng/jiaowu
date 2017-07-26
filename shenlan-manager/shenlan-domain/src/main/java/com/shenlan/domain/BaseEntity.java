package com.shenlan.domain;


import java.io.Serializable;

public class BaseEntity implements Serializable
{

	private static final long	serialVersionUID	= 1841646150515368881L;

	private Long				id;

	private Long[]				delIds;


	/**
	 * @return the removeIds
	 */
	public Long[] getDelIds()
	{
		return delIds;
	}

	/**
	 * @param removeIds
	 *            the removeIds to set
	 */
	public void setDelIds(Long[] delIds)
	{
		this.delIds = delIds;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

}
