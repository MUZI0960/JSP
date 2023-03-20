package kr.or.ddit.props.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.props.vo.PropertyVO;

public class PropertyDAOImpl_DBTest {

	private PropertyDAO dao = new PropertyDAOImpl_DB();

	@Test
	public void testSelectProperties() {
		List<PropertyVO> list = dao.selectProperties(null);
		assertNotNull(list);
		assertNotEquals(0, list.size());
	}

	@Test(expected = RuntimeException.class)
	public void testInsertProperty() {
		dao.insertProperty(new PropertyVO());
	}

}
