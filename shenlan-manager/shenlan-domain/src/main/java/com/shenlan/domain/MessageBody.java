package com.shenlan.domain;


/**
 * ajax返回实体
 */
public class MessageBody
{

	private Boolean	success;
	private int		errorCode;
	private String	errorInfo;
	private Object	data;
	private String	message;
	private String	stackTrace;

	/**
	 * @return the success
	 */
	public Boolean getSuccess()
	{
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(Boolean success)
	{
		this.success = success;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode()
	{
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorInfo
	 */
	public String getErrorInfo()
	{
		return errorInfo;
	}

	/**
	 * @param errorInfo
	 *            the errorInfo to set
	 */
	public void setErrorInfo(String errorInfo)
	{
		this.errorInfo = errorInfo;
	}

	/**
	 * @return the data
	 */
	public Object getData()
	{
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data)
	{
		this.data = data;
	}

	/**
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * @return the stackTrace
	 */
	public String getStackTrace()
	{
		return stackTrace;
	}

	/**
	 * @param stackTrace
	 *            the stackTrace to set
	 */
	public void setStackTrace(String stackTrace)
	{
		this.stackTrace = stackTrace;
	}
}
