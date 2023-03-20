package kr.or.ddit.schema.service;

import java.util.List;

import kr.or.ddit.schema.dao.ColumnSchemaDAO;
import kr.or.ddit.schema.dao.ColumnSchemaDAOImpl;
import kr.or.ddit.schema.dao.TableSchemaDAO;
import kr.or.ddit.schema.dao.TableSchemaDAOImpl;
import kr.or.ddit.vo.ColumnSchemaVO;
import kr.or.ddit.vo.TableSchemaVO;

public class SchemaServiceImpl implements SchemaService {

	private TableSchemaDAO tdao = new TableSchemaDAOImpl();
	private ColumnSchemaDAO cdao = new ColumnSchemaDAOImpl(); 
	
	@Override
	public List<TableSchemaVO> retrieveTableSchemaList() {
		return tdao.selectTableSchemaList();
	}

	@Override
	public List<ColumnSchemaVO> retrieveColumnSchemaListByTableName(String tableName) {
		return cdao.selectColumnSchemaListByTableName(tableName);
	}

}
