package com.shenlan.common.exception;

/**
 * 系统错误，编码10000开始
 */
public enum SystemErrorCode implements ErrorCode
{

	/**
	 * 系统内部错误
	 */
	SYSTEM_ERROR(10000),
	/**
	 * 读取数据出现超时，请检查网络情况
	 */
	READ_TIMED_OUT_ERROR(10001),
	/**
	 * 用户停用
	 */
	USER_DISABLE_ERROR(10002),
	/**
	 * 用户名不存在
	 */
	NOT_USER_ERROR(10003),
	/**
	 * 用户名或密码错误
	 */
	USER_PASSWORD_ERROR(10004),
	/**
	 * 登录认证错误，请重试
	 */
	SHIRO_LOGIN_FAILURE(10005),
	
	/**
	 * 测试系统异常
	 */
	TEST_SYS_EXCEPTION_ERROR(10006);
	
	private final int	number;

	private SystemErrorCode(int number)
	{
		this.number = number;
	}

	public int getNumber()
	{
		return number;
	}

}
