package com.lrmin.onekey.resolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理实体类的域
 */
public final class EntityFieldResolver {

	// 包含自己的域和其直接父类的域
	private Field[] fields;
	//需要处理的域
	private Field[] resolverFields;

	public EntityFieldResolver(Class<?> clazz) throws ClassNotFoundException {
		Field[] thisFields = clazz.getDeclaredFields();
		Field[] superClassFields = clazz.getSuperclass().getDeclaredFields();

		fields  = new Field[thisFields.length + superClassFields.length];
		System.arraycopy(superClassFields, 0, fields, 0, superClassFields.length);
		System.arraycopy(thisFields, 0, fields, superClassFields.length, thisFields.length);

		resolverFields = this.fields;
	}

	public void excludeField(String fieldName) {

		List<Field> newList = new ArrayList<Field>();
		for(Field field : this.fields) {
			if (!fieldName.equals(field.getName())){
				newList.add(field);
			}
		}
		this.resolverFields = (Field[]) newList.toArray();
	}

	public Field[] getFields() {
		return fields;
	}

	public Field[] getResolverFields() {
		return resolverFields;
	}

}
