package com.lrmin.onekey;

import java.net.URISyntaxException;

public class PathManager {
	private String projectPath;
	private String lastPath = "src\\main\\java\\";
	private final String serviceFloderName = "service";
	private final String daoFloderName = "dao";
	private final String mapperFloderName = "mapper";
	private final String entityFloderName = "entity";
	private final String tableCraeteSqlFloderName = "";
	private final String contorllerFloderName = "web";
	private String mudelPath;
	private Class<?> clazz;
	
	private static PathManager pathManager;

	public String getDaoPath() throws URISyntaxException {
		return projectPath + lastPath + mudelPath + daoFloderName;
	}

	public String getServicePath() throws URISyntaxException {
		return projectPath + "\\" + lastPath + mudelPath + "\\"+serviceFloderName +"\\";
	}
	public String getMapperPath() throws URISyntaxException {
		return projectPath + "\\" + lastPath + mudelPath + "\\"+mapperFloderName +"\\";
	}
	public String getTableCraeteSqlPath() throws URISyntaxException {
		return projectPath + "\\" + lastPath + mudelPath + "\\"+tableCraeteSqlFloderName +"\\";
	}
	public String getContorllerPath() {
		return projectPath + "\\" + lastPath + mudelPath + "\\"+contorllerFloderName +"\\";
	}

	private PathManager(Class<?> clazz) {
		this.clazz = clazz;
		initMudelPath(clazz);
		initprojectPath();
	}
	public static PathManager getInstence(Class<?> clazz) {
		if(pathManager == null) {
			pathManager = new PathManager(clazz);
		}
		return pathManager;
	}
	
	private void initMudelPath(Class<?> clazz) {
		String clazzSempleName = clazz.getName();
		String[] strs = clazzSempleName.split("\\.");
		StringBuilder lastPath = new StringBuilder();
		for (int i = 0; i < strs.length - 2; i++) {
			lastPath.append(strs[i]);
			lastPath.append("\\");
		}
		this.mudelPath = lastPath.toString() +"\\";
	}

	private void initprojectPath() {
		this.projectPath = System.getProperty("user.dir");
	}

	
}
