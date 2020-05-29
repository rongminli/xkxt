package com.lrmin.onekey.generator;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URISyntaxException;

import com.lrmin.framework.service.AbstractService;
import com.lrmin.onekey.OutPuter;
import com.lrmin.utils.StringUtils;

public class ServiceGenerator {
	private Class<?> beanClass;
	private Class<?> mapperClass;
	private OutPuter outPuter;
	
	private String serviceText ="";
	
	public String generatService() throws URISyntaxException {
		serviceText += "package " + this.getPackeName()+";\n\n";
		serviceText += this.getImporPackges();
		serviceText += "@Service \npublic class "+this.getInterFaceClazzName();
		serviceText += "  extends AbstractService<" + beanClass.getSimpleName() + ", " + mapperClass.getSimpleName() + ">  {\n";
		serviceText += "	@Autowired\n";
		serviceText += "	private "+mapperClass.getSimpleName()+" mapper;\n\n";
		serviceText += this.getExitMethod() + "\n\n";
//		serviceText += this.getMethods();
		serviceText += "}";
		
		outPuter.createServiceFile();
		outPuter.outPutService(serviceText);
		System.out.println("service generat complete");
		return serviceText;
	}

	private String getExitMethod(){
		return "@Override\n" +
				"\tpublic boolean exit("+ beanClass.getSimpleName() +" entity) {\n" +
				"\t\treturn findById(entity.getId()) == null;\n" +
				"\t}";
	}
	private String getMethods() {
		StringBuilder str = new StringBuilder();
		Method[] methods = mapperClass.getMethods();
		for(Method method:methods) {
			str.append("	public ");
			str.append(this.getReturnType(method));
			String methodName = method.getName();
			str.append(" ").append(methodName);
			str.append(this.getMethodArgs(method));
			str.append("{\n");
			str.append(this.getReturn(method));
			str.append("	}\n\n");
		}
		return str.toString();
	}
	private String getReturnType(Method mapperMethod) {
		String str = "";
		Type returnType = mapperMethod.getGenericReturnType();
		str += getTypeSimpleName(mapperMethod.getReturnType().getSimpleName());
		if(returnType instanceof ParameterizedType) {
			str += "<";
			ParameterizedType pt = (ParameterizedType) returnType;
			str += getTypeSimpleName(pt.getActualTypeArguments()[0].getTypeName())+">";
		}
		return str;
	}
	private String getTypeSimpleName(String typeName) {
		return typeName.substring(typeName.lastIndexOf(".")+1, typeName.length());
	}
	private String getMethodArgs(Method mapperMethod) {
		StringBuilder str = new StringBuilder();
		Parameter[] parameters = mapperMethod.getParameters();
		str.append("(");
		for(int i=0;i<parameters.length;i++) {
			Parameter parameter = parameters[i];
			String pTypeName = parameter.getType().getSimpleName();
			String pName = StringUtils.toLowerCaseFirstOne(pTypeName);
			str.append(pTypeName).append(" ").append(pName);
			if(i < parameters.length-1) {
				str.append(", ");
			}
		}
		str.append(")");
		return str.toString();
	}
	private String getReturn(Method mapperMethod) {
		StringBuilder str = new StringBuilder();
		str.append("		return mapper.").append(mapperMethod.getName());
		Parameter[] parameters = mapperMethod.getParameters();
		str.append("(");
		for(int i=0;i<parameters.length;i++) {
			Parameter parameter = parameters[i];
			String pName = StringUtils.toLowerCaseFirstOne(parameter.getType().getSimpleName());
			str.append(pName);
			if(i < parameters.length-1) {
				str.append(", ");
			}
		}
		str.append(");\n");
		return str.toString();
	}
	private String getPackeName() {
		String pakageName = beanClass.getPackage().getName();
		return pakageName.substring(0, pakageName.lastIndexOf(".")) + ".service";
	}
	private String getImporPackges() {
		String str = "import org.springframework.stereotype.Service; \n";
		str += "import org.springframework.beans.factory.annotation.Autowired;\n";
		str += "import java.util.List;\n";
		str += "import com.lrmin.framework.service.AbstractService;\n";
		str += "import "+mapperClass.getName()+";\n";
		str += "import "+beanClass.getName()+";\n";
		str += "\n";
		return str;
	}
	private String getInterFaceClazzName() {
		return beanClass.getSimpleName()+"Service";
	}
	public ServiceGenerator(Class<?> mapperClass,Class<?> beanClass) throws URISyntaxException, ClassNotFoundException {
		this.beanClass = beanClass;
		this.mapperClass = mapperClass;
		outPuter = OutPuter.getInstance(beanClass);
	}

	public String getServiceClassName() {
		return this.getPackeName()+"."+this.getInterFaceClazzName();
	}
}
