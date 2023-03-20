package kr.or.ddit.props.service;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import kr.or.ddit.props.vo.PropertyVO;

public class PropertyServiceImpl2 extends PropertyServiceImpl1 {
	@Override
	public List<PropertyVO> retrieveProperties(String propertyName) {
		List<PropertyVO> list =  super.retrieveProperties(propertyName);
		list.stream()
			.forEach(System.out::println);
		return list;
	}
}
