package com.lrmin.onekey.generator;


import com.lrmin.onekey.OutPuter;
import com.lrmin.onekey.resolver.EntityFieldResolver;
import com.lrmin.onekey.resolver.EntityResolver;
import com.lrmin.onekey.resolver.NameTranslator;

import java.lang.reflect.Field;
import java.net.URISyntaxException;

public class TableCreatSqlGenerator {
	private Class<?> beanClass;
	private OutPuter outPuter;
	private EntityResolver entityResolver;
	private String tableCreatSqlText = "";
	
	public TableCreatSqlGenerator(Class<?> clazz) throws URISyntaxException, ClassNotFoundException {
		this.beanClass = clazz;
		outPuter = OutPuter.getInstance(clazz);
		entityResolver = new EntityResolver(clazz);
		
	}
	
	public void generatTableCreatSql() throws URISyntaxException {
		tableCreatSqlText += "DROP TABLE " + NameTranslator.getTableName(beanClass) + ";\n";
		tableCreatSqlText += "CREATE TABLE " + NameTranslator.getTableName(beanClass) + "(\n";
		tableCreatSqlText += getClums();
		tableCreatSqlText += "); \n\n";
		tableCreatSqlText += this.getIdSequence() + "; \n\n";
		tableCreatSqlText += this.getTrigger()+";\n";
		
		outPuter.createTableCreateSqlFile();
		outPuter.outPutTableCreatSql(tableCreatSqlText);
		System.out.println("tableCreatSql generat complete");
	}
	public String getClums() {
		StringBuilder str = new StringBuilder();
		EntityFieldResolver entityFieldResolver = entityResolver.getEntityFieldResolver();
		for(Field field: entityFieldResolver.getResolverFields()) {
			String fieldName = field.getName();
			String columnName = NameTranslator.camelToUnderline(fieldName);
			String JDBCType = NameTranslator.getClumJDBCType(field);
			
			if ("VARCHAR".equals(JDBCType)) {
				JDBCType += "(100)";
			}
			str.append(columnName).append(" ").append(JDBCType);
			str.append(",");
		}
		str.delete(str.length()-1, str.length());
		return str.toString();
	}
	public String getIdSequence() {
		String str = "CREATE SEQUENCE " + NameTranslator.getIdSequenceName(beanClass) +"\n";
		str += "INCREMENT BY 1                      \r\n" +
				"START WITH 1                         \r\n" +
				"NOMAXVALUE                    \r\n" +
				"MINVALUE 1                             \r\n" +
				"NOCYCLE                    \r\n" +
				"CACHE 20                               \r\n" +
				"ORDER   ";
		return str;
	}
	private String getTrigger() {
		return "create or replace trigger "+NameTranslator.getTableName(beanClass)+"\r\n" +
				"before insert on "+NameTranslator.getTableName(beanClass)+"\r\n" +
				"for each row\r\n" + 
				"begin\r\n" + 
				"select "+NameTranslator.getIdSequenceName(beanClass)+".nextval into :new.id from dual;\r\n" +
				"end";
		
	}
	
}
