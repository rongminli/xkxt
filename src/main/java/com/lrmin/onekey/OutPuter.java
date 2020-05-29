package com.lrmin.onekey;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

public class OutPuter {
	private static OutPuter outPuter;
	private PathManager pathManager;
	private File mapperFile;
	private File mapperInterFaceFile;
	private File serviceFile;
	private File tableCreateSqlFile;
	private File controllerFile;
	private Class<?> clazz;
	protected static boolean isBackup = true;
	
	private OutPuter(Class<?> clazz) throws URISyntaxException{
		this.clazz = clazz;
		pathManager = PathManager.getInstence(clazz);
	};
	
	public static void setIsBackup(boolean isBack){
		isBackup = isBack;
	}
	public static OutPuter getInstance(Class<?> clazz) throws URISyntaxException {
		if(outPuter==null) outPuter = new OutPuter(clazz);
		return outPuter;
	}
	public void createTableCreateSqlFile() throws URISyntaxException {
		File dir = new File(pathManager.getTableCraeteSqlPath());
		dir.mkdirs();
		tableCreateSqlFile = new File(pathManager.getTableCraeteSqlPath()+clazz.getSimpleName()+".sql");
		try {
			backUp(tableCreateSqlFile);
			tableCreateSqlFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void createMapperFile() throws URISyntaxException  {
		File dir = new File(pathManager.getMapperPath());
		dir.mkdirs();
		mapperFile = new File(pathManager.getMapperPath()+clazz.getSimpleName()+"Mapper.xml");
		backUp(mapperFile);
		try {
			mapperFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void createMapperInterFaceFile() throws URISyntaxException {
		File dir = new File(pathManager.getMapperPath());
		mapperInterFaceFile = new File(pathManager.getMapperPath()+clazz.getSimpleName()+"Mapper.java");
		try {
			dir.mkdirs();
			backUp(mapperInterFaceFile);
			mapperInterFaceFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void createServiceFile() throws URISyntaxException {
		File dir = new File(pathManager.getServicePath());
		serviceFile= new File(pathManager.getServicePath()+clazz.getSimpleName()+"Service.java");
		try {
			dir.mkdirs();
			backUp(serviceFile);
			serviceFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void createControllerFile() throws URISyntaxException {
		File dir = new File(pathManager.getContorllerPath());
		controllerFile= new File(pathManager.getContorllerPath()+clazz.getSimpleName()+"Controller.java");
		try {
			dir.mkdirs();
			backUp(controllerFile);
			controllerFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void outPutMapper(String mapperText) {
		outPut(mapperText,mapperFile);
	}
	public void outPutMapperInterFace(String mapperInterFaceText) {
		outPut(mapperInterFaceText,mapperInterFaceFile);
	}
	public void outPutService(String serviceText) {
		outPut(serviceText,serviceFile);
	}
	
	public void outPutTableCreatSql(String tableCreatSqlText) {
		outPut(tableCreatSqlText,tableCreateSqlFile);		
	}
	public void outPutController(String controllerText) {
		outPut(controllerText,controllerFile);		
	}
	private void outPut(String text,File file) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(file.getAbsoluteFile());
			out.print(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
	private void backUp(File file) {
		if(isBackup) {
			int i = 1;
			while(file.exists()) {
				String fileName = file.getName() + "-副本"+i;
				String path = file.getParentFile().getPath();
				file.renameTo(new File(path+"\\"+fileName));
				i++;
			}
		}
	}

	
}
