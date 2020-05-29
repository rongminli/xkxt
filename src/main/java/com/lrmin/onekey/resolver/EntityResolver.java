package com.lrmin.onekey.resolver;

public class EntityResolver {
	private EntityFieldResolver entityFieldResolver;
	private Class<?> EntityClass;

	public EntityResolver(Class<?> EntityClass) throws ClassNotFoundException {
		this.EntityClass = EntityClass;
		entityFieldResolver = new EntityFieldResolver(EntityClass);
	}

	/**
	 * 获取所有需要处理的域的名称
	 * @return
	 */
	public EntityFieldResolver getEntityFieldResolver() {
		return entityFieldResolver;
	}

	public void excludeField(String fieldName){
		entityFieldResolver.excludeField(fieldName);
	}
}
