package ptest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperBeanConverter implements BeanConverter {

	ObjectMapper om = new ObjectMapper() ;

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


		C bean = om.convertValue(source, targetClass);

		return bean;
	}
}
