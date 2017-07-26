package com.shenlan.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shenlan.common.exception.SystemException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.shenlan.common.exception.ErrorCode;
import com.shenlan.common.exception.SystemErrorCode;


/**
 * 公共方法
 */
public class Com
{
	static
	{
		ConvertUtils.register(new DateConvert(), java.util.Date.class);
	}

	/**
	 * 保留小数位
	 */
	public final static int	SCALE				= 4;

	private static Pattern	dPattern				= Pattern.compile("\\d+$");		// 数字
	private static Pattern	wPattern				= Pattern.compile("[a-zA-Z]+$");	// 英文字母

	/**
	 * 是否有值 : hasValue
	 */
	public static boolean hv(String rs)
	{
		return rs != null && rs.length() > 0;
	}

	/**
	 * 是否有值 : hasValue
	 */
	public static boolean hv(Integer rs)
	{
		return rs != null && rs != 0;
	}

	/**
	 * 是否有值 : hasValue
	 */
	public static boolean hv(Double rs)
	{
		return rs != null && rs != 0d;
	}

	/**
	 * 是否有值 : hasValue
	 */
	public static boolean hv(Date rs)
	{
		return rs != null;
	}

	/**
	 * 是否有值 : hasValue
	 */
	public static boolean hv(Long rs)
	{
		return rs != null && rs != 0l;
	}

	/**
	 * 是否有值 : hasValue
	 */
	public static boolean hv(String[] str)
	{
		return str != null && str.length > 0;
	}

	/**
	 * 是否有值 : hasValue
	 * <h1>注意：如果list的第一个元素是null那么返回false</h1>
	 */
	public static boolean hv(List<?> list)
	{
		if (list != null && list.size() > 0)
		{
			if (hv(list.get(0)))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 是否有值 : hasValue
	 */
	public static boolean hv(Object obj)
	{
		return obj != null;
	}

	/**
	 * 是否有值 : hasValue
	 */
	public static boolean hv(Object[] obj)
	{
		return obj != null && obj.length > 0;
	}

	/**
	 * 是否有值 : hasValue
	 * 
	 * <h1>注意：该方法主要用于判断多个参数同时不为null时才用</h1>
	 * <h2> 用法:Scm.hv(obj1,obj2,obj3,...,args)</h2>
	 * 
	 * @param obj
	 *            参数1
	 * @param args
	 *            参数列表
	 */
	public static boolean hv(Object obj, Object... args)
	{
		if (!hv(obj))
		{
			return false;
		}
		for (Object arg : args)
		{
			if (!hv(arg))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * 其中一个参数是否有值 : oneHasValue
	 * 
	 * @param args
	 *            参数列表
	 */
	public static boolean oneHv(Object... args)
	{
		for (Object arg : args)
		{
			if (arg instanceof String)
			{ // 如果类型是字符串特殊处理
				if (hv(Com.ts(arg)))
				{
					return true;
				}
			}
			else
			{
				if (hv(arg))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 是否相等 : equals
	 * 
	 * <h1>注意：src,dest其中一个值不为null</h1>
	 * <h2>用法:Scm.eq(null,1); Scm.eq(1,2); Scm.eq(2,null);等</h2>
	 * 
	 * @param src
	 *            源字符串
	 * @param dest
	 *            目标字符串
	 */
	public static boolean eq(Object src, Object dest)
	{
		if (src == null && dest == null) return true;
		return hv(src) ? src.equals(dest) : dest.equals(src);
	}

	/**
	 * 转换成String : toString
	 * @param obj
	 */
	public static String ts(Object obj)
	{
		return hv(obj) ? String.valueOf(obj) : null;
	}

	/**
	 * 转换成String : toString
	 * @param rs
	 */
	public static String ts(String rs)
	{
		return rs == null ? "" : rs;
	}

	/**
	 * SQL拼接中单引号处理 : singleQuoteMark
	 * @param rs
	 */
	public static String sqm(String rs)
	{
		return replace(rs, "'", "''");
	}

	/**
	 * 字符串替换
	 * 
	 * 注意：不需要判断rs == null
	 * 
	 * @param rs
	 *            原字符串
	 * @param target
	 *            需要替换的内容
	 * @param replacement
	 *            替换成的内容
	 */
	public static String replace(String rs, CharSequence target, CharSequence replacement)
	{
		return rs == null ? "" : rs.replace(target, replacement);
	}

	/**
	 * 转换成Integer : toInteger
	 * @param obj
	 */
	public static Integer ti(String rs)
	{
		return hv(rs) ? Integer.parseInt(rs) : null;
	}

	/**
	 * 转换成有效的Integer类型
	 */
	public static Integer ti(Integer rs)
	{
		return hv(rs) ? rs : 1;
	}

	/**
	 * 转换成Integer : toInteger
	 */
	public static Integer ti(Long rs)
	{
		return hv(rs) ? rs.intValue() : null;
	}

	/**
	 * 转换成Double : toDouble
	 */
	public static Double td(String rs)
	{
		return hv(rs) ? Double.parseDouble(rs) : null;
	}

	/**
	 * 转换成有效的Double类型 : toAmount
	 */
	public static Double ta(Double rs)
	{
		return hv(rs) ? rs : 0.00;
	}

	/**
	 * 转换成有效的Double类型 : toAmount
	 */
	public static Double ta(Long rs)
	{
		return hv(rs) ? Double.parseDouble(Com.ts(rs)) : 0.00;
	}

	/**
	 * 转换成Long : toLong
	 */
	public static Long tl(String str)
	{
		return hv(str) ? Long.parseLong(str) : null;
	}

	/**
	 * 转换成Long : toLong
	 */
	public static Long tl(Integer i, Long dec)
	{
		return hv(i) ? Long.valueOf(i) : dec;
	}

	/**
	 * 转换成Long : toLong
	 */
	public static Long tl(Integer i)
	{
		return hv(i) ? Long.valueOf(i) : null;
	}

	/**
	 * 转换成有效的Long类型 : toAmount
	 */
	public static Long tl(Long rs)
	{
		return hv(rs) ? rs : 0;
	}

	/**
	 * Exception输出 ：printStackTrace
	 */
	public static void pe(Exception e)
	{
		e.printStackTrace();
	}

	/**
	 * 日期格式 : formatDate
	 */
	public static String fd(Date date)
	{
		return fd(date, "yyyy-MM-dd");
	}

	/**
	 * 日期格式 : formatDate
	 */
	public static String fd(Date date, String args)
	{
		return hv(date) ? new SimpleDateFormat(args).format(date) : null;
	}

	/**
	 * 日期格式 : formatDate
	 */
	public static String fdA(Date date, String args)
	{
		// date.setHours(23);
		// date.setMinutes(59);
		// date.setSeconds(59);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		// date.setTime(999999);
		return hv(c.getTime()) ? new SimpleDateFormat(args).format(c.getTime()) : null;
	}

	/**
	 * 得到一天中的最大时间
	 */
	public static Date fdA(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		// date.setTime(999999);
		return c.getTime();
	}

	/**
	 * 返回list.size() : listSize
	 */
	public static Integer ls(List<Object> list)
	{
		return hv(list) ? list.size() : 0;
	}

	// Double精度 start

	/**
	 * 相加 : Add
	 */
	public static Double padd(Double v1, Double v2)
	{
		return DoubleHelper.add(ta(v1), ta(v2), SCALE);
	}

	/**
	 * 相减 : Sub
	 */
	public static Double psub(Double v1, Double v2)
	{
		return DoubleHelper.sub(ta(v1), ta(v2), SCALE);
	}

	/**
	 * 相乘 : Mul
	 */
	public static Double pmui(Double v1, Double v2)
	{
		return DoubleHelper.mul(ta(v1), ta(v2), SCALE);
	}

	/**
	 * 相除 : Div
	 */
	public static Double pdiv(Double v1, Double v2)
	{
		return DoubleHelper.div(ta(v1), ta(v2), SCALE);
	}

	/**
	 * 精度 : Round
	 */
	public static Double pround(Double v1)
	{
		return DoubleHelper.round(ta(v1), SCALE);
	}

	/**
	 * 精度 : Round
	 */
	public static Double pround(Double v1, Integer scale)
	{
		return DoubleHelper.round(ta(v1), scale);
	}

	/**
	 * 转绝对值
	 */
	public static Double abs(Double v1)
	{
		return new BigDecimal(v1).abs().doubleValue();
	}

	/**
	 * 凑整
	 */
	public static Double ceil(Double v1)
	{
		return Math.ceil(v1);
	}

	// Double精度 end

	/**
	 * 某天星期几
	 * 
	 * 注意：1:星期日 2:星期一 3:星期二 4:星期三 5:星期四 6:星期五 7:星期六
	 */
	public static Integer getDayOfWeek(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 判断字符串中是否包含中文
	 * 
	 * 注意 : 如果str为null返回false
	 */
	public static boolean isChinese(String str)
	{
		return hv(str) && Pattern.compile("[\\u4e00-\\u9fa5]+").matcher(str).find();
	}

	/**
	 * 字符串首字母是否为数字
	 * 
	 * 注意 : 如果str为null返回false
	 */
	public static boolean isNaN(String str)
	{
		return hv(str) && Character.isDigit(str.charAt(0));
	}

	/**
	 * 字符串首字母是否为字母
	 * 
	 * 注意 : 如果str为null返回false
	 */
	public static boolean isLetter(String str)
	{
		return hv(str) && Character.isLetter(str.charAt(0));
	}

	/**
	 * 字符串转成日期
	 * 
	 * @param str
	 *            日期字符串：例如：2011-11-11
	 * @param pattern
	 *            日期格式 例如 ： yyyy-MM-dd
	 */
	public static Date str2Date(String str, String pattern)
	{
		if (str == null) return null;

		SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern);
		ParsePosition parseposition = new ParsePosition(0);
		Date date = simpledateformat.parse(str, parseposition);
		return date;
	}

	/**
	 * 字符串转成日期
	 * 
	 * 注意：日期格式"yyyy-MM-dd"
	 * 
	 * @param str
	 *            日期字符串：例如：2011-11-11
	 */
	public static Date str2Date(String str)
	{
		return str2Date(str, "yyyy-MM-dd");
	}

	/**
	 * 截取
	 */
	public static String tn(String str)
	{
		if (Com.hv(str))
		{
			if (str.contains("|"))
			{
				return str.split("\\|")[1];
			}
			else
			{
				return str;
			}
		}
		return str;
	}

	/**
	 * bool为true，返回exp1；为false，返回exp2
	 */
	public static String nvl(boolean bool, String exp1, String exp2)
	{
		return bool ? exp1 : exp2;
	}

	/**
	 * 得到月末最后一天
	 */
	public static String ldom(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return fd(calendar.getTime());
	}

	/**
	 * 得到月末最后一天
	 * @return
	 */
	public static String fdom(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return fd(calendar.getTime());
	}

	/**
	 * 获取日期
	 */
	public static int getDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 是否数字
	 */
	public static boolean isDigital(String code)
	{
		Matcher dMatcher = dPattern.matcher(code);
		return dMatcher.matches();
	}

	/**
	 * 是否英文字母
	 */
	public static boolean isCharacter(String code)
	{
		Matcher wMatcher = wPattern.matcher(code);
		return wMatcher.matches();
	}
	//exception begin
	
	public static void assertEquest(Object src, Object dest, ErrorCode errorCode)
	{
		assertEquest(src, dest, errorCode, null);
	}

	public static void assertEquest(Object src, Object dest, ErrorCode errorCode, String message)
	{
		if (Com.eq(src, dest))
		{
			throw SystemException.getInstance(message, errorCode);
		}
	}

	public static void assertNotEquest(Object src, Object dest, ErrorCode errorCode)
	{
		assertNotEquest(src, dest, errorCode, null);
	}

	public static void assertNotEquest(Object src, Object dest, ErrorCode errorCode, String message)
	{
		if (!Com.eq(src, dest))
		{
			throw SystemException.getInstance(message, errorCode);
		}
	}

	public static void assertNotNull(Object object, ErrorCode errorCode)
	{
		assertNotNull(object, errorCode, null);
	}

	public static void assertNotNull(Object object, ErrorCode errorCode, String message)
	{
		if (object == null)
		{
			throw SystemException.getInstance(message, errorCode);
		}
	}

	public static void assertRepeat(Boolean isRepeat, ErrorCode errorCode)
	{
		if (isRepeat)
		{
			throw SystemException.getInstance(errorCode);
		}
	}

	public static void assertRepeat(Boolean isRepeat, ErrorCode errorCode, Object... formats)
	{
		if (isRepeat)
		{
			throw SystemException.getInstance(errorCode).formats(formats);
		}
	}
	//exception end
	
	public static <T> T copyProperties(T dest, Object orig)
	{
		try
		{
			BeanUtils.copyProperties(dest, orig);
		}
		catch (Exception e)
		{
			throw SystemException.wrap(e, SystemErrorCode.SYSTEM_ERROR);
		}
		return dest;
	}

	public static String decodeURI(String code, String encoding)
	{
		try
		{
			return java.net.URLDecoder.decode(code, encoding);
		}
		catch (UnsupportedEncodingException e)
		{
			Com.pe(e);
		}
		return "";
	}

	public static Integer getSqlInCount(Integer length)
	{
		Integer count = 1;
		if (Constant.SCM_IN_MAX_LENGTH >= 0)
		{
			count = length / Constant.SCM_IN_MAX_LENGTH;
			if (length % Constant.SCM_IN_MAX_LENGTH == 0)
			{
				return count;
			}
			return count + 1;
		}
		return count;
	}

	/**
	 * 两个日期之间相差的天数
	 */
	public static int daysBetween(Date start, Date end)
	{
		start = Com.str2Date(Com.fd(start));
		end = Com.str2Date(Com.fd(end));
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		long time1 = cal.getTimeInMillis();
		cal.setTime(end);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 比较两个list的值是否相等
	 * @return true 表示相等,不计较排序
	 */
	public static boolean eqListValue(List<? extends Object> obj1, List<? extends Object> obj2)
	{
		if (null == obj1 || null == obj2)
		{
			throw new RuntimeException("eqList 不能传入null 对象,请查看.");
		}
		return obj1.containsAll(obj2) && obj2.containsAll(obj1);
	}
}
