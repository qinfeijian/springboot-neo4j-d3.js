package com.example.demo.utils;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 作者:梁克飙
 * 日期:2018年1月10日
 * 说明:字符串工具类
 */
/**
 * @author Mymomo
 *
 */
/**
 * @author Mymomo
 *
 */
public class StringUtil {

    /**
     * 
     * 作者:梁克飙
     * 日期:2017年7月27日
     * @param bd 一个数字
     * @return
     * 返回:String 序号
     * 说明:数字前面填充0，最多9999，超过就超过5位
     */
    public static String fillZoneMax4(BigDecimal bd) {
        
        if (bd == null) {
            return "0000";
        }
        
        int val = bd.intValue() + 1;
        if (val > 9999) {
            return bd.toString();
        } else if(val < 10) {
            return "000" + val;
        } else if(val < 999) {
            return "00" + val;
        } else if(val < 9999) {
            return "0" + val;
        }
        return "9999";
    }
    
	/**
	 * 
	 * 作者:梁克飙
	 * 日期:2018年1月10日
	 * @param str 某个值
	 * @return
	 * 返回:boolean 是否空
	 * 说明:判断是否空
	 */
	public static boolean isEmpty(Object str) {
		if (str == null || "".equals(str.toString()) || "null".equals(str.toString()) || "undefined".equals(str.toString())) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 作者:梁克飙
	 * 日期:2018年1月10日
	 * @param str 某个值
	 * @return
	 * 返回:boolean 是否不空
	 * 说明:判断是否不空
	 */
	public static boolean notEmpty(Object str) {
		return !isEmpty(str);
	}

	/**
	 * 
	 * 作者:梁克飙
	 * 日期:2018年1月10日
	 * @param ob object
	 * @return
	 * 返回:String 字符串
	 * 说明:object转字符串
	 */
	public static String getString(Object ob) {
		if (ob == null) {
			return null;
		}
		return ob.toString();
	}
	
	
	/**
	 * @param date
	 * @param format
	 * @return String
	 * 说明：返回日期字符串
	 */
	public static String Date2Str(Date date, String format) {
		String result = "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			result = dateFormat.format(date);
		} catch (Exception e) {
			
		}
		return result;
	}
	
	public static Date getDate(Object obj, String format) {
		if (isEmpty(obj)) {
			return null;
		} else {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			try {
				return dateFormat.parse(obj.toString());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
}
