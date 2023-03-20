package kr.or.ddit.schema.dao;

import java.util.List;

import kr.or.ddit.vo.TableSchemaVO;

public interface TableSchemaDAO {
	
	/**
	 * 
	 * 전체 테이블의 목록 조회.
	 * 존재하지 않는 경우, .size()==0
	 * @return 
	 */
	public List<TableSchemaVO> selectTableSchemaList();
	
}
