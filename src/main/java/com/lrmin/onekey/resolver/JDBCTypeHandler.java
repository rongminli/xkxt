package com.lrmin.onekey.resolver;

import java.lang.reflect.Field;

import org.apache.ibatis.type.JdbcType;

public class JDBCTypeHandler {

	public enum  JDBCType{
	   VARCHAR,NUMBER,TIMESTAMP;
	}
	
	public static String getJDBCTypeForField(Field field) {
		String feildType = field.getGenericType().getTypeName();
		switch(feildType) {
			case "java.lang.String": return JdbcType.VARCHAR.toString();
			case "int": return JdbcType.INTEGER.toString();
			case "java.lang.Long": return JdbcType.INTEGER.toString();
			case "java.lang.Integer": return JdbcType.INTEGER.toString();
			case "java.util.Date": return JdbcType.TIMESTAMP.toString();
			case "java.lang.Double": return JdbcType.DOUBLE.toString();
			case "java.lang.Float": return JdbcType.FLOAT.toString();
			case "java.lang.Boolean": return JdbcType.CHAR.toString();
		}
		return "";
	}
}
