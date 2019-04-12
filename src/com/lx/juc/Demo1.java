package com.lx.juc;

import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import com.lx.other.Customer;

/**
 * 
 * @ClassName: PointCutCache
 * @Description: TODO(切面拦截)
 *
 */
public class Demo1 {

	/*
	 * private final Logger logger = Logger.getLogger(this.getClass());
	 * 
	 * private static Object obc = new Object();
	 * 
	 *//**
		 * 参数类 包路径
		 */
	/*
	 * private String strPageUrl;
	 * 
	 *//**
		 * 
		 * @MethodName: doAround
		 * @Description: TODO(通知拦截)
		 * @param jp 切面信息
		 * @return 数据
		 */
	/*
	 * public Object doAround(ProceedingJoinPoint jp) {
	 * 
	 * try { if (null != jp) { String strKey = getKey(jp);//拼接memcache的key Object
	 * obcData = null; //obcData是查询结果 obcData = CacheUtil.getKeyCache(strKey); if
	 * (null != obcData) { return obcData; } else { if (jp.getArgs().length > 0) {
	 * obcData = jp.proceed(jp.getArgs());//运行service中的查询方法真正进行查询 } else { obcData =
	 * jp.proceed(); } synchronized (obc) { CacheUtil.setCache(strKey, obcData,
	 * DateUtil.addMinutes(new Date(), 30)); } return obcData; } }
	 * 
	 * } catch (Exception e) { logger.error("缓存失败:", e); } catch (Throwable e) {
	 * logger.error("缓存失败:", e); } return null; }
	 * 
	 *//**
		 * 
		 * @MethodName: getKey
		 * @Description: TODO(获取memcache的KEY)
		 * @param jp 参数
		 * @return key
		 * @throws IllegalAccessException
		 * @throws IllegalArgumentException
		 * @throws NoSuchMethodException
		 * @throws SecurityException
		 * @throws InvocationTargetException
		 *//*
			 * @SuppressWarnings("rawtypes") private String getKey(ProceedingJoinPoint jp)
			 * throws IllegalArgumentException, IllegalAccessException, SecurityException,
			 * NoSuchMethodException, InvocationTargetException {
			 * 
			 * StringBuffer strBF = new StringBuffer();
			 * strBF.append(jp.getTarget().getClass().getName() +
			 * ".");//全类名com.sf.ieca.common.base.service.impl.CommonServiceImpl
			 * strBF.append(jp.getSignature().getName());//方法名 Object[] obc = jp.getArgs();
			 * if (obc.length > 0) { for (Object obcParam : obc) { Class<? extends Object>
			 * paramClazz = obcParam.getClass();//参数类型 String typeName =
			 * paramClazz.getName(); //简单参数
			 * 使用拼接com.sf.ieca.common.base.service.impl.CommonServiceImpl.
			 * getCustomerByCmCodeE000000773 if (typeName.equals("java.lang.String") ||
			 * typeName.equals("java.lang.Integer") || typeName.equals("java.lang.Boolean")
			 * || typeName.equals("java.lang.Double") ||
			 * typeName.equals("java.lang.StringBuffer") ||
			 * typeName.equals("java.lang.Short")) { strBF.append(obcParam.toString());
			 * //hashMap用全类名+key } else if (typeName.equals("java.util.HashMap")) { HashMap
			 * has = (HashMap) obcParam; if (has.size() > 0) { Iterator iterator =
			 * has.keySet().iterator(); while (iterator.hasNext()) {
			 * strBF.append(has.get(iterator.next())); } } //如果是自定义类作为参数, 则通过反射拼接字段名作参数 }
			 * else if (typeName.startsWith(strPageUrl)) { Field[] files =
			 * paramClazz.getDeclaredFields(); if (files.length > 0) { for (Field field :
			 * files) { String fieldName = field.getName(); String firstLetter =
			 * fieldName.substring(0, 1).toUpperCase(); field.setAccessible(true);//
			 * 允许访问私有字段 String getMethodName = "get" + firstLetter + fieldName.substring(1);
			 * Method getMethod = paramClazz.getMethod(getMethodName, new Class[] {});
			 * Object obcValue = getMethod.invoke(obcParam, new Object[] {}); if (null !=
			 * obcValue) { strBF.append(obcValue.toString()); } } }
			 * 
			 * } } } //key加密后才能使用 return PasswordEncryption.encrypt(strBF.toString()); }
			 * 
			 * public void setStrPageUrl(String strPageUrl) { this.strPageUrl = strPageUrl;
			 * }
			 */

}