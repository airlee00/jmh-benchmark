package ptest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jodd.bean.BeanUtil;


public class JoddBeanConverter implements BeanConverter {

	public <T extends Map<String, Object>, C> List<C> convertMapToBean(
			List<T> list, Class<C> clazz) {
		List<C> beanList = new ArrayList<C>();
		for (Map<String, Object> source : list) {
			C bean = toBean(source, clazz);
			beanList.add(bean);

		}
		return beanList;
	}

	private <C> C toBean(Map<String, Object> source, Class<C> targetClass) {

		C bean = null;
		try {
			bean = targetClass.newInstance();
			Field[] _declaredFields = targetClass.getDeclaredFields();
			for (Field field : _declaredFields) {
				Object value = source.get(field.getName());
				if (value != null) {
					BeanUtil.declared.setProperty(bean, field.getName(), value);
				}
			}
		} catch (InstantiationException e) {
			new IllegalArgumentException("Cannot initiate class",e);
		} catch (IllegalAccessException e) {
			new IllegalStateException("Cannot access the property",e);
		}
		return bean;
	}
}
