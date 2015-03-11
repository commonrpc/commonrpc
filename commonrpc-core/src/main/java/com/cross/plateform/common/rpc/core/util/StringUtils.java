/**
 * 
 */
package com.cross.plateform.common.rpc.core.util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author liubing
 * string 工具类对象
 */
public class StringUtils {
	
	 private static final String EMPTY = "";
	/**
	 * 判断是否为空
	 * @param paramObject
	 * @return
	 */
	public static boolean isNullOrEmpty(Object paramObject) {
		return (paramObject == null)
				|| ("".equals(paramObject.toString()))
				|| (paramObject.equals("null") || paramObject.toString().trim()
						.equals(""));
	}
	
	/**
	 * 转为string 对象
	 * @param paramObject
	 * @return
	 */
	public static String toString(Object paramObject) {
		if (paramObject == null)
			return "null";
		return paramObject.toString();
	}
	
	/**
    * 将一个字符串重复 N 次
    * 
    * @param str
    *            要重复的字符串
    * @param times
    *            重复次数
    * @return
    */
   public static String repeat(String str, int times) {
       StringBuilder sb = new StringBuilder(str.length() * times);
       for (int i = 0; i < times; i++) {
           sb.append(str);
       }
       return sb.toString();
   }
   
   /**
    * 连接字符串
    * 
    * @param elements
    *            要连接的数组
    * @param separator
    *            分隔符
    * @return 连接结果
    */
   public static String join(Object[] elements, String separator) {
       if (elements == null) {
           return EMPTY;
       }
       if (separator == null) {
           separator = EMPTY;
       }

       StringBuilder sb = new StringBuilder(elements.length * 16);

       for (int i = 0; i < elements.length; i++) {
           if (i > 0) {
               sb.append(separator);
           }
           sb.append(elements[i]);
       }
       return sb.toString();
   }
   
   public static String repeat(Object str, String separator, int times) {
       Object[] array = new Object[times];
       Arrays.fill(array, str);
       return join(array, separator);
   }
   /**
    * 是否是基础类型
    * @param object
    * @return
    */
   public static final boolean isPrimaryType(Object object) {
       return object instanceof Byte || object instanceof Short
               || object instanceof Integer || object instanceof Long
               || object instanceof Character || object instanceof Float
               || object instanceof Double || object instanceof Boolean;
   }
   
   /**
    * array 转化string 对象
    * @param object
    * @return
    */
   public static final String arrayToString(Object object) {
       StringBuilder sb = new StringBuilder();
       if (!object.getClass().isArray())
           return sb.toString();
       int len = Array.getLength(object);
       sb.append("[");
       for (int i = 0; i < len; i++) {
           Object obj = Array.get(object, i);
           String str = obj == null ? "null" : toString(obj);
           if (isPrimaryType(obj)) {
               sb.append(str);
           } else {
               sb.append("(").append(str).append(")");
           }
           if (i < len - 1) {
               sb.append(", ");
           }
       }
       sb.append("]");
       return sb.toString();
   }
   
}
