package com.shenlan.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.converter.HttpMessageNotReadableException;

import com.shenlan.common.utils.PropertiesLoader;




public class SystemException extends RuntimeException
{

	private static final long			serialVersionUID	= 1L;

	private ErrorCode					errorCode;

	private Object[]					formats;

	private final Map<String, Object>	properties			= new TreeMap<String, Object>();

	public static SystemException wrap(Throwable exception, ErrorCode errorCode)
	{
		if (exception instanceof SystemException)
		{
			return (SystemException) exception;
		}
		else
		{
			return new SystemException(exception.getMessage(), exception, errorCode);
		}
	}

	public static SystemException convertSystemException(Throwable exception)
	{
		if (exception instanceof SystemException)
		{
			return (SystemException) exception;
		}
		else if (exception instanceof HttpMessageNotReadableException && exception.getCause() != null && exception.getCause() instanceof SocketTimeoutException)
		{
			return new SystemException(exception, SystemErrorCode.READ_TIMED_OUT_ERROR);
		}
		else
		{
			return new SystemException(exception, SystemErrorCode.SYSTEM_ERROR);
		}
	}

	public static SystemException getInstance(ErrorCode errorCode)
	{
		return getInstance(null, errorCode);
	}

	public static SystemException getInstance(String message, ErrorCode errorCode)
	{
		return new SystemException(message, errorCode);
	}

	public SystemException(ErrorCode errorCode)
	{
		this.errorCode = errorCode;
	}

	public SystemException(String message, ErrorCode errorCode)
	{
		super(message);
		this.errorCode = errorCode;
	}

	public SystemException(Throwable cause, ErrorCode errorCode)
	{
		super(cause);
		this.errorCode = errorCode;
	}

	public SystemException(String message, Throwable cause, ErrorCode errorCode)
	{
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode()
	{
		return errorCode;
	}

	public String getErrorInfo()
	{
		String key = errorCode.getClass().getSimpleName() + "_" + errorCode;
		return String.format(PropertiesLoader.getProperty(key.toUpperCase(), ""), this.formats);
	}

	public SystemException setErrorCode(ErrorCode errorCode)
	{
		this.errorCode = errorCode;
		return this;
	}

	public Map<String, Object> getProperties()
	{
		return properties;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String name)
	{
		return (T) properties.get(name);
	}

	public SystemException set(String name, Object value)
	{
		properties.put(name, value);
		return this;
	}

	public SystemException set(Map<?, ?> map)
	{
		for (Object key : map.keySet())
		{
			properties.put(String.valueOf(key), map.get(key));
		}
		return this;
	}

	public SystemException formats(Object... formats)
	{
		this.formats = formats;
		return this;
	}

	/**
	 * 将ErrorStack转化为String.
	 */
	public String getStackTraceAsString()
	{
		StringWriter stringWriter = new StringWriter();
		this.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	public void printStackTrace(PrintStream s)
	{
		synchronized (s)
		{
			printStackTrace(new PrintWriter(s));
		}
	}

	public void printStackTrace(PrintWriter s)
	{
		synchronized (s)
		{
			if (!properties.isEmpty())
			{
				s.println("\t-------------------------------");
				for (String key : properties.keySet())
				{
					s.println("\t" + key + "=[" + properties.get(key) + "]");
				}
			}
			if (errorCode != null)
			{
				s.println("\t-------------------------------");
				s.println("\t" + errorCode + ":" + errorCode.getClass().getName());
			}
			s.println("\t-------------------------------");
			StackTraceElement[] trace = getStackTrace();
			for (int i = 0; i < trace.length; i++)
				s.println("\tat " + trace[i]);

			Throwable ourCause = getCause();
			if (ourCause != null)
			{
				ourCause.printStackTrace(s);
			}
			s.flush();
		}
	}
}
