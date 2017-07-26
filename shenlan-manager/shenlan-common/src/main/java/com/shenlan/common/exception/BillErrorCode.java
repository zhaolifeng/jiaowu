package com.shenlan.common.exception;

/**
 * 
 */
public enum BillErrorCode implements ErrorCode
{
	
	/**
	 * 测试普通异常
	 */
	TEST_COMM_EXCEPTION_ERROR(10001);

	private final int	number;

	private BillErrorCode(int number)
	{
		this.number = number;
	}

	public int getNumber()
	{
		return number;
	}

}
