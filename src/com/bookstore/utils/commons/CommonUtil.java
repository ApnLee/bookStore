package com.bookstore.utils.commons;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

public class CommonUtil {
	/*
	 * 用UUID产生随机字符串不会重复
	 * 用其来产生ID是一个很好的选择
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	/*
	 * 将Map转换为JavaBean的工具类
	 */
	@SuppressWarnings("rawtypes")
	public static <T> T toBean(Class<T> clazz,Map map){
		try {
			T bean = clazz.newInstance();
			ConvertUtils.register(new DateConverter(), java.util.Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
