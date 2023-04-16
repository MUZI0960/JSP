package kr.or.ddit.schema.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.schema.dao.ColumnSchemaDAO;
import kr.or.ddit.schema.dao.TableSchemaDAO;
import kr.or.ddit.vo.ColumnSchemaVO;
import kr.or.ddit.vo.TableSchemaVO;

@Service
public class SchemaServiceImpl implements SchemaService {
	@Inject
	private TableSchemaDAO tableDAO;
	// 의존관계 형성을 위한 코드로 발생하는 강결합을 DI(의존성주입) 구조를 통해 해결해야 함.
	@Autowired
	private ColumnSchemaDAO colDAO;

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















