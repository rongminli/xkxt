package com.lrmin.onekey.generator;

import com.lrmin.onekey.OutPuter;

import java.net.URISyntaxException;

public class MapperInterFaceGenertor {
	private Class<?> clazz;
	private OutPuter outPuter;
	private String mapperInterfaceText = "";
	
	public MapperInterFaceGenertor(Class<?> clazz) throws URISyntaxException, ClassNotFoundException {
		this.clazz = clazz;
		outPuter = OutPuter.getInstance(clazz);
	}
	public String genertMapperInterFace() throws URISyntaxException {
		mapperInterfaceText += this.getPackeName()+"\n";
		mapperInterfaceText += this.getImporPackges();
		mapperInterfaceText += "@Mapper \npublic interface "+this.getInterFaceClazzSimpleName();
		mapperInterfaceText += " extends Dao<"+clazz.getSimpleName()+">";
		mapperInterfaceText += " {\n\n";
//		mapperInterfaceText += this.getInsert()+"\n";
//		mapperInterfaceText += this.getInsertSelective()+"\n";
//		mapperInterfaceText += this.getDeleteById()+"\n";
//		mapperInterfaceText += this.getUpDate()+"\n";
//		mapperInterfaceText += this.getUpDateSelective()+"\n";
//		mapperInterfaceText += this.getFindAll()+"\n";
//		mapperInterfaceText += this.getFindById()+"\n";
		mapperInterfaceText += "}";
		
		outPuter.createMapperInterFaceFile();
		outPuter.outPutMapperInterFace(mapperInterfaceText);
		
		System.out.println("mapper interface generat complete");
		return mapperInterfaceText;
	}
	private String getPackeName() {
		String pakageName = clazz.getPackage().getName();
		return "package " + pakageName.substring(0, pakageName.lastIndexOf(".")) + ".mapper;\n";
	}
	private String getImporPackges() {
		String str = "import org.apache.ibatis.annotations.Mapper; \n";
		str += "import java.util.List;\n";
		str += "import com.lrmin.framework.mapper.Dao;\n";
		str += "import "+clazz.getName()+";\n";
		str += "\n";
		return str;
	}
	public String getInterFaceClazzSimpleName() {
		return clazz.getSimpleName()+"Mapper";
	}
	public String getInterFaceClazzName() {
		String pakageName = clazz.getPackage().getName();
		return pakageName.substring(0, pakageName.lastIndexOf(".")) + ".mapper."+clazz.getSimpleName()+"Mapper";
	}
	private String getInsert() {
		return "	public Long insert("+clazz.getSimpleName()+" "+"obj); \n";
	}
	private String getInsertSelective() {
		return "	public Long insertSelective("+clazz.getSimpleName()+" "+"obj); \n";
	}
	private String getDeleteById() {
		return "	public Long deleteById(String id); \n";
	}
	private String getUpDate() {
		return "	public Long update("+clazz.getSimpleName()+" "+"obj); \n";
	}
	private String getUpDateSelective() {
		return "	public Long updateSelective("+clazz.getSimpleName()+" "+"obj); \n";
	}
	private String getFindAll() {
		return "	public List<"+clazz.getSimpleName()+"> findAll(); \n";
	}
	private String getFindById() {
		return "	public List<"+clazz.getSimpleName()+"> findById(String id); \n";
	}
}
