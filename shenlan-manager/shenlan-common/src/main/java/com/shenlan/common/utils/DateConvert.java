package com.shenlan.common.utils;

import org.apache.commons.beanutils.converters.DateTimeConverter;

/**
 * 处理Date null 复制错误
 */
public class DateConvert extends DateTimeConverter
{

	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Class type, Object value)
	{
		if (value == null)
		{
			return null;
		}
		return super.convert(type, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class getDefaultType()
	{
		return java.util.Date.class;
	}

}
