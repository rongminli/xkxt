package com.lrmin.onekey.resolver;

import java.lang.reflect.Field;

import com.github.pagehelper.util.StringUtil;

public class NameTranslator {
	
	private static  String tableNamePrefix = "T";
	private static  String clumNamePrefix = "";
	private static String specifiedTableName = "";
	
	public static void specifiedTableName(String tableName) {
		specifiedTableName = specifiedTableName;
	}

	public static String getTableName(Class<?> beanClass) {
		if(!StringUtil.isEmpty(specifiedTableName)) {
			return specifiedTableName;
		}
		return tableNamePrefix +"_"+ beanClass.getSimpleName().toUpperCase();
	};
	
	public static String getTableName(Class<?> beanClass,String prefix) {
		return prefix +"_"+ beanClass.getSimpleName().toUpperCase();
	};
	
	public static String getIdSequenceName(Class<?> beanClass) {
		return "ID_"+getTableName(beanClass)+"_SEQUENCE";
	}
	
	public static String getClumName(Field field) {
		return clumNamePrefix.equals("") ? field.getName():(clumNamePrefix+"_"+field.getName());
	}
	public static String getClumJDBCType(Field field) {
		return JDBCTypeHandler.getJDBCTypeForField(field);
	}
	
	public static String getClumName(Field field,String prefix) {
		return prefix+"_"+field.getName();
	}
	
	public static String getTableNamePrefix() {
		return tableNamePrefix;
	}

	public static void setClumNamePrefix(String prefix) {
		clumNamePrefix = prefix;
	}
	public static void setTableNamePrefix(String prefix) {
		tableNamePrefix = prefix;
	}

	public static String getMapperNameSpace(Class entityClass) {
		StringBuilder str = new StringBuilder();
		String[] strs = entityClass.getName().split("\\.");
		for (int i = 0; i < strs.length - 2; i++) {
			str.append(strs[i]);
			str.append(".");
		}
		str.append("mapper.");
		str.append(entityClass.getSimpleName());
		str.append("Mapper");
		return str.toString();
	}

	public static final char UNDERLINE = '_';
	/**
	 * 驼峰格式字符串转换为下划线格式字符串
	 *
	 * @param param
	 * @return
	 */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 下划线格式字符串转换为驼峰格式字符串
	 *
	 * @param param
	 * @return
	 */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == UNDERLINE) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
