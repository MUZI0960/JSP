package kr.or.ddit.schema.service;

import java.util.List;

import kr.or.ddit.schema.dao.ColumnSchemaDAO;
import kr.or.ddit.schema.dao.ColumnSchemaDAOImpl;
import kr.or.ddit.schema.dao.TableSchemaDAO;
import kr.or.ddit.schema.dao.TableSchemaDAOImpl;
import kr.or.ddit.vo.ColumnSchemaVO;
import kr.or.ddit.vo.TableSchemaVO;

public class SchemaServiceImpl implements SchemaService {

	private TableSchemaDAO tableDAO = new TableSchemaDAOImpl();
	// 의존관계 형셩을 위한 코드로 발생하는 강결합을 DI(의존성 주입) 구조를 통해 해결해야 함.
	private ColumnSchemaDAO colDAO = new ColumnSchemaDAOImpl();
	
	@Override
	public List<TableSchemaVO> retrieveTableSchemaList() {
		List<TableSchemaVO> list = tableDAO.selectTableSchemaList();
		if(list.isEmpty())
			throw new RuntimeException("테이블이 하나도 없음.");
		return list;
	}

	@Override
	public List<ColumnSchemaVO> retrieveColumnSchemaListByTableName(String tableName) {
		List<ColumnSchemaVO> list = colDAO.selectColumnSchemaListByTableName(tableName);
		if(list.isEmpty())
			throw new RuntimeException(String.format("%s 테이블의 컬럼이 없음", tableName));
		return list;
	}

}
