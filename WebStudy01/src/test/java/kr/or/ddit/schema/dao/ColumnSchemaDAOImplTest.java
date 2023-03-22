package kr.or.ddit.schema.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.ColumnSchemaVO;

public class ColumnSchemaDAOImplTest {

	private ColumnSchemaDAO colDAO = new ColumnSchemaDAOImpl();

	@Test
	public void testSelectColumnSchemaListByTableName() {
		List<ColumnSchemaVO> list = colDAO.selectColumnSchemaListByTableName("MEMBER");
		assertNotNull(list);
		assertNotEquals(0, list.size());
	}

}
