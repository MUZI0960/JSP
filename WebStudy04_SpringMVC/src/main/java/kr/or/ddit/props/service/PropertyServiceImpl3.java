package kr.or.ddit.props.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.vo.PropertyVO;

@Service("propService3")
public class PropertyServiceImpl3 implements PropertyService {
	@Inject
	@Named("propertyDAO")
	private PropertyDAO dao;

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
