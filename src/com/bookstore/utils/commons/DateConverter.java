package com.bookstore.utils.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.beanutils.Converter;

public class DateConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class type, Object value) {
		if(value==null) return null;
		if(!(value instanceof String))
			return value;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse((String) value);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
