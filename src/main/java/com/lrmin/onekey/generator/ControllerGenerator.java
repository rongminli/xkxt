package com.lrmin.onekey.generator;

import java.net.URISyntaxException;

import com.lrmin.framework.contoller.AbstractController;
import com.lrmin.onekey.OutPuter;
import com.lrmin.utils.StringUtils;

public class ControllerGenerator {
	private Class<?> entityClass;
	private Class<?> serviceClass;
	private OutPuter outPuter;
	
	private String controllerText ="";
	
	public ControllerGenerator(Class<?> serviceClass,Class<?> entityClass) throws URISyntaxException, ClassNotFoundException {
		this.entityClass = entityClass;
		this.serviceClass = serviceClass;
		outPuter = OutPuter.getInstance(entityClass);
	}
	
	public void generatorContorller() throws URISyntaxException {
		controllerText += this.getPackName()+"\n";
		controllerText += this.getImporPackges();
		controllerText += "@RestController\r\n" + 
				"@RequestMapping(\"/"+
				StringUtils.toLowerCaseFirstOne(entityClass.getSimpleName())
				+"\") \n"
				+ "public class "+this.getInterFaceClazzName()
				+ "  extends AbstractController<"
				+ entityClass.getSimpleName() + ", " + serviceClass.getSimpleName()
				+">" +" {\n";
//		controllerText += "	@Autowired\n";
//		controllerText += "	private "+serviceClass.getSimpleName()+" service;\n\n";
		controllerText += "}";
		
		outPuter.createControllerFile();
		outPuter.outPutController(controllerText);
		System.out.println("controller generat complete");
	}
	
	private String getPackName() {
		String pakageName = entityClass.getPackage().getName();
		return "package " + pakageName.substring(0, pakageName.lastIndexOf(".")) + ".web;\n";
	}
	private String getImporPackges() {
		String str = "import org.springframework.web.bind.annotation.RestController; \n";
		str += "import org.springframework.web.bind.annotation.RequestMapping;\n";
		str += "import org.springframework.beans.factory.annotation.Autowired;\n";
		str += "import com.lrmin.framework.contoller.AbstractController;\n";
		str += "import "+serviceClass.getName()+";\n";
		str += "import "+entityClass.getName()+";\n";
		str += "\n";
		return str;
	}
	private String getInterFaceClazzName() {
		return entityClass.getSimpleName()+"Controller";
	}
}
