package kr.or.ddit.props.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.props.dao.PropertyDAO;
import kr.or.ddit.props.vo.PropertyVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("propService1")
public class PropertyServiceImpl1 implements PropertyService {
	@Resource(name="propDao_FS")
	private PropertyDAO dao;
	
	@Inject
	private WebApplicationContext container;

	@PostConstruct
	public void init() {
		log.info("주입된 컨테이너 레퍼런스 : {}", container);
	}
	
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
		return dao.deleteProperty(removeProp)>0;
	}

}
