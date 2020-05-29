package com.lrmin.onekey.generator;

import com.lrmin.onekey.OutPuter;
import com.lrmin.onekey.resolver.EntityFieldResolver;
import com.lrmin.onekey.resolver.EntityResolver;
import com.lrmin.onekey.resolver.NameTranslator;

import java.lang.reflect.Field;
import java.net.URISyntaxException;

public class MapperGenerator {
	private Class<?> entityClass;
	private OutPuter outPuter;
	private EntityResolver entityResolver;
	private String mapperText = "";
	private static String tableName = "";
	
	
	public static void setTableName(String _tableName) {
		tableName = _tableName;
	}
	private final String MapperHead = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?> \n"
			+"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"> \n";
	private final String boot = "</mapper>";
	
	public MapperGenerator(Class<?> entityClass) throws URISyntaxException, ClassNotFoundException {
		this.entityClass = entityClass;
		outPuter = OutPuter.getInstance(entityClass);
		entityResolver = new EntityResolver(entityClass);
		if ("".equals(tableName)) {
			tableName = NameTranslator.getTableName(entityClass);
		}
	}
	public void generatMapper() throws URISyntaxException {
		String mapperNameSpace = NameTranslator.getMapperNameSpace(entityClass);

		mapperText += MapperHead;
		mapperText += "<mapper namespace=\""+ mapperNameSpace +"\"> \n\n";
		mapperText += getBaseResultMap()+"\n";
		mapperText += getClumList()+"\n";
		mapperText += getInsert()+"\n";
		mapperText += this.getInsertSelective()+"\n";
		mapperText += this.getDeleteById()+"\n";
		mapperText += this.getUpDate()+"\n";
		mapperText += this.getUpdateSelective()+"\n";
		mapperText += this.getFindAll()+"\n";
		mapperText += this.getFindById()+"\n";
		mapperText += this.getFindBy()+"\n";
		mapperText += this.getSearch() + "\n";
		mapperText += boot;
		
		outPuter.createMapperFile();
		outPuter.outPutMapper(mapperText);
		
		System.out.println("mapper generat complete");
	}



	private String getBaseResultMap() {
		StringBuilder str =new StringBuilder();
		str.append("<resultMap id=\"BaseResultMap\" type=\""+ entityClass.getName()+"\"> \n");
		
		EntityFieldResolver entityFieldResolver = entityResolver.getEntityFieldResolver();
		for(Field field: entityFieldResolver.getResolverFields()) {
			if(field.getName().equals("id")) {
				String fieldJDBCType = NameTranslator.getClumJDBCType(field);
				str.append( "	<id column=\"id\" property=\"id\" jdbcType=\"").append(fieldJDBCType).append("\"/> \n");
			}else{
				String fieldName = field.getName();
				String columnName = NameTranslator.camelToUnderline(fieldName);
				String fieldJDBCType = NameTranslator.getClumJDBCType(field);
				
				str.append("	<result column=\"").append(columnName);
				str.append("\" property=\"").append(fieldName).append("\"")
				.append(" jdbcType=\"").append(fieldJDBCType).append("\"/> \n");
			}
		}
		str.append("</resultMap> \n");
		return str.toString();
	}
	

	private String getClumList() {
		StringBuilder str =new StringBuilder();
		str.append("<sql id=\"baseClumList\"> ");
		EntityFieldResolver entityFieldResolver = entityResolver.getEntityFieldResolver();
		for(Field field: entityFieldResolver.getResolverFields()) {
			String fieldName = field.getName();
			String columnName = NameTranslator.camelToUnderline(fieldName);
			str.append(columnName).append(", ");
		}
		str.delete(str.length()-2,str.length()-1);
		str.append(" </sql> \n");
		return str.toString();
	}

	private String getInsert() {
		StringBuilder str =new StringBuilder();
		str.append("<insert id=\"insert\"> \n");
		str.append("	INSERT INTO ").append(tableName).append("(");
		EntityFieldResolver entityFieldResolver = entityResolver.getEntityFieldResolver();
		str.append(" <include refid=\"baseClumList\"/> ");
		str.append(") VALUES( \n		");
		for(Field field: entityFieldResolver.getResolverFields()) {
			str.append(getRefStr(field));
			str.append(", ");
		}
		str.delete(str.length()-2,str.length()-1);
		str.append("\n	) \n</insert> \n");
		return str.toString();

	}

	private String getInsertSelective() {
		StringBuilder str =new StringBuilder();
		str.append("<insert id=\"insertSelective\"> \n");
		str.append("	INSERT INTO ").append(tableName);
		
		str.append("\n	<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" > \n");
		EntityFieldResolver entityFieldResolver = entityResolver.getEntityFieldResolver();
		for(Field field: entityFieldResolver.getResolverFields()) {
			String fieldName = field.getName();
			String columnName = NameTranslator.camelToUnderline(fieldName);
			
			str.append("		<if test=\"").append(field.getName()).append("!= null\"> ").append(columnName).append(", </if> \n");
		}
		str.append("	</trim> \n");
		
		str.append("	VALUES \n");
		str.append("	<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" > \n");
		for(Field field: entityFieldResolver.getResolverFields()) {
			str.append("		<if test=\"").append(field.getName()).append("!= null\"> ").append(this.getRefStr(field)).append(", </if> \n");
		}
		str.append("	</trim> \n");
		str.append("</insert> \n");
		return str.toString();
	}
	private String getDeleteById() {
		return  "<delete id=\"deleteById\">\r\n" + 
				"	DELETE FROM "+ tableName +" WHERE id=#{id,jdbcType=VARCHAR}\r\n" + 
				"</delete> \n";
	}
	private String getUpDate() {
		StringBuilder str =new StringBuilder();
		str.append("<update id=\"update\"> \n	UPDATE "+ tableName +" set ");
		
		EntityFieldResolver entityFieldResolver = entityResolver.getEntityFieldResolver();
		for(Field field: entityFieldResolver.getResolverFields()) {
			String fieldName = field.getName();
			String columnName = NameTranslator.camelToUnderline(fieldName);
			
			str.append(columnName).append("=").append(getRefStr(field));
			str.append(",");
		}
		str.delete(str.length()-1,str.length());
		str.append(" where id=#{id}");
		str.append("\n</update> \n");
		return str.toString();
	}

	private String getUpdateSelective() {
		StringBuilder str =new StringBuilder();
		str.append("<update id=\"updateSelective\"> \n");
		str.append("	UPDATE ").append(tableName).append(" set\n");
		
		EntityFieldResolver entityFieldResolver = entityResolver.getEntityFieldResolver();
		str.append("	<trim prefix=\"\" suffix=\"\" suffixOverrides=\",\" > \n");
		for(Field field: entityFieldResolver.getResolverFields()) {
			String fieldName = field.getName();
			String columnName = NameTranslator.camelToUnderline(fieldName);
			
			str.append("		<if test=\"").append(field.getName()).append("!= null\"> ")
			.append(columnName).append("=").append(getRefStr(field)).append(", </if>\n");
		}
		str.append("	</trim>\n");
		str.append("	where id=#{id}\n");
		str.append("</update> \n");
		return str.toString();
	}
	private String getFindAll() {
		return "<select id=\"findAll\" resultMap=\"BaseResultMap\">\r\n" + 
				"	select <include refid=\"baseClumList\"/> from "+ tableName +"\r\n" + 
				"</select> \n";
	}

	private String getFindById() {
		return "<select id=\"findById\" resultMap=\"BaseResultMap\">\r\n" + 
				"	select <include refid=\"baseClumList\"/> from "+ tableName +" where id=#{id}\r\n" + 
				"</select> \n";
	}

	private String getFindBy() {
		return "<select id=\"findBy\" resultMap=\"BaseResultMap\">\r\n" +
				"	select <include refid=\"baseClumList\"/> from "+ tableName +" where 1=1\r\n" +
				getSelectiveCondi() +
				"</select> \n";
	}

	private String getSearch(){
		return "<select id=\"search\" resultMap=\"BaseResultMap\">\n" +
				"\t\tselect <include refid=\"baseClumList\"/> from T_COURSE\n" +
				"\t\twhere 1=1\n" +
				"\t</select>";
	}

	/**
	 * 获取{filedName,JDBCType = type} 这样的字符串
	 * @param field
	 * @return
	 */
	private String getRefStr(Field field) {
		return "#{"
				+field.getName() 
				+",jdbcType="
				+ NameTranslator.getClumJDBCType(field)
				+"}";
	}

	private String getSelectiveCondi(){
		StringBuilder str = new StringBuilder();
		EntityFieldResolver entityFieldResolver = entityResolver.getEntityFieldResolver();

		str.append("	<trim prefix=\"\" suffix=\"\" suffixOverrides=\",\" > \n");
		for(Field field: entityFieldResolver.getResolverFields()) {
			String fieldName = field.getName();
			String columnName = NameTranslator.camelToUnderline(fieldName);

			str.append("		<if test=\"").append(field.getName()).append("!= null\"> AND ")
					.append(columnName).append("=").append(getRefStr(field)).append(", </if>\n");
		}
		str.append("	</trim>\n");

		return str.toString();
	}
	public EntityResolver getEntityResolver() {
		return entityResolver;
	}
}
