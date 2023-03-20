package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.vo.PropertyVO;

public interface PropertyService {
	public List<PropertyVO> retrieveProperties(String propertyName);
	public boolean createProperty(PropertyVO newProp);
	public boolean modifyProperty(PropertyVO modifyProp);
	public boolean removeProperty(PropertyVO removeProp);
}
