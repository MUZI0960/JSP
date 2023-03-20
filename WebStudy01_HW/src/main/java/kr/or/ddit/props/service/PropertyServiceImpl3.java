package kr.or.ddit.props.service;

import java.util.List;

import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.dao.PropertyDAOImpl_DB;
import kr.or.ddit.props.vo.PropertyVO;

public class PropertyServiceImpl3 implements PropertyService {

	private PropertyDAO dao = new PropertyDAOImpl_DB();
	
	@Override
	public List<PropertyVO> retrieveProperties(String propertyName) {
		return dao.selectProperties(propertyName);
	}

	@Override
	public boolean createProperty(PropertyVO newProp) {
		return false;
	}

	@Override
	public boolean modifyProperty(PropertyVO modifyProp) {
		return false;
	}

	@Override
	public boolean removeProperty(PropertyVO removeProp) {
		return false;
	}

}
