package kr.or.ddit.props.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.props.vo.PropertyVO;

public class PropertyDAOImpl_FSTest {

	private PropertyDAO dao = new PropertyDAOImpl_FS();

	@Test
	public void testSelectProperties() {
		 dao.selectProperties(null).stream()
		 			.forEach((p)->{
		 				System.out.println(p);
		 			});
	}

	@Test
	public void testInsertProperty() {
		PropertyVO newProp = new PropertyVO();
		newProp.setPropertyName("prop4");
		newProp.setPropertyValue("프로퍼티값4");
		dao.insertProperty(newProp);
	}

}
