package kr.or.ddit.props.service;

import java.time.LocalDateTime;
import java.util.List;

import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.dao.PropertyDAOImpl_FS;
import kr.or.ddit.props.vo.PropertyVO;

public class PropertyServiceImpl1 implements PropertyService {
	private PropertyDAO dao = new PropertyDAOImpl_FS();
	
	@Override
	public List<PropertyVO> retrieveProperties(String propertyName) {
		List<PropertyVO> propertyList = dao.selectProperties(null);
		for(PropertyVO prop : propertyList) {
			prop.setPropertyValue(String.format("%s[%s]", prop.getPropertyValue(), LocalDateTime.now()));
		}
		return propertyList;
	}

	@Override
	public boolean createProperty(PropertyVO newProp) {
		int rowcnt = dao.insertProperty(newProp);
		return rowcnt > 0;
	}

	@Override
	public boolean modifyProperty(PropertyVO modifyProp) {
		return dao.updateProperty(modifyProp) > 0;
	}

	@Override
	public boolean removeProperty(PropertyVO removeProp) {
		return dao.deleteProperty(removeProp) > 0;
	}

}
