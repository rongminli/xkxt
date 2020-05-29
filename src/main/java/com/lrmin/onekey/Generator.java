package com.lrmin.onekey;

import java.net.URISyntaxException;

import com.lrmin.onekey.classLoader.JavaStrSrcloader;
import com.lrmin.onekey.generator.*;
import com.lrmin.onekey.resolver.NameTranslator;

public class Generator {
	private Class<?> beanClass;
	private static MapperGenerator mapperGenerator;
	private MapperInterFaceGenertor mapperInterFaceGenertor;
	private ServiceGenerator serviceGenerator;
	private TableCreatSqlGenerator tableCreatSqlGenerator;
	private ControllerGenerator controllerGenerator;
	
	public static void addExcludeFieldName(String fieldName) {
		mapperGenerator.getEntityResolver().excludeField(fieldName);
	}
	public static void setTableNamePrefix(String prefix) {
		NameTranslator.setTableNamePrefix(prefix);
	}
	public static void setisBackup(boolean isBack) {
		OutPuter.setIsBackup(isBack);
	}
	public static void setTableName(String _tableName) {
		MapperGenerator.setTableName(NameTranslator.getTableNamePrefix()+"_"+_tableName);
		NameTranslator.specifiedTableName(_tableName);
	}
	public void generat() {
		try {
			tableCreatSqlGenerator.generatTableCreatSql();
			mapperGenerator.generatMapper();
			
			String mapperInterFaceJavaStrsrc = mapperInterFaceGenertor.genertMapperInterFace();
			this.initServiceGeneratorWithStr(mapperInterFaceJavaStrsrc);
			
			String serviceStrsrc = serviceGenerator.generatService();
			this.initControllerGeneratorWithService(serviceStrsrc);
			
			controllerGenerator.generatorContorller();

			System.out.println("generation complete");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	public Generator(Class<?> beanClass) throws ClassNotFoundException, URISyntaxException {
		this.beanClass = beanClass;
		mapperGenerator = new MapperGenerator(beanClass);
		mapperInterFaceGenertor = new MapperInterFaceGenertor(beanClass);
		tableCreatSqlGenerator = new TableCreatSqlGenerator(beanClass);
	}
	/**
	 * 用mapper接口类字符串实例化service生成器
	 * @param mapperInterFaceJavaStrsrc mapper接口类字符串
	 */
	private void initServiceGeneratorWithStr(String mapperInterFaceJavaStrsrc) {
		try {
			Class<?> mapperClass = JavaStrSrcloader.compile(mapperInterFaceGenertor.getInterFaceClazzName(), mapperInterFaceJavaStrsrc);
			serviceGenerator = new ServiceGenerator(mapperClass,beanClass);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据service类实例化controller生成器kh
	 */
	private void initControllerGeneratorWithService(String serviceStrsrc) {
		try {
			Class<?> serviceClass = JavaStrSrcloader.compile(serviceGenerator.getServiceClassName(), serviceStrsrc);
			this.controllerGenerator = new ControllerGenerator(serviceClass,this.beanClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	public void updataMapperXml() {
		try {
			mapperGenerator.generatMapper();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
