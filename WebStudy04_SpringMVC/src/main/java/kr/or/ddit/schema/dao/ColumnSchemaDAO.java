package kr.or.ddit.schema.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.ColumnSchemaVO;
@Mapper
public interface ColumnSchemaDAO {
	/**
	 * 특정 테이블의 컬럼 리스트 조회
	 * 존재하지 않더라도, .size()==0
	 * @param tableName
	 * @return 
	 */
	public List<ColumnSchemaVO> selectColumnSchemaListByTableName(String tableName);
}
