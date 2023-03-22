package kr.or.ddit.schema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.ColumnSchemaVO;

public class ColumnSchemaDAOImpl implements ColumnSchemaDAO {
	
//	persistence framework (sql mapper, data mapper orm framework) : iBatis, myBatis, JPA, Hibernate ...
	@Override
	public List<ColumnSchemaVO> selectColumnSchemaListByTableName(String tableName) {
		StringBuffer sql = new StringBuffer();
		// 1. 쿼리 결정 : sql mapper
		 sql.append(" SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE, DATA_LENGTH, NULLABLE   ");
		 sql.append(" FROM USER_TAB_COLUMNS                       ");
		 sql.append(" WHERE TABLE_NAME = ?                ");
		 List<ColumnSchemaVO> list = new ArrayList<>();
		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			// 2. 쿼리 파라미터 설정
			pstmt.setString(1, tableName);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 3. 결과집합 -> POJO(VO) : data mapper 프레임웍이 동작할 때 reflection 방식을 사용함.
//				ColumnSchemaVO vo = ColumnSchemaVO.builder()
//									.tableName(rs.getString("TABLE_NAME"))
//									.columnName(rs.getString("COLUMN_NAME"))
//									.dataType(rs.getString("DATA_TYPE"))
//									.dataLength(rs.getInt("DATA_LENGTH"))
//									.nullable(rs.getString("NULLABLE"))
//									.build();
////						new ColumnSchemaVO();
				list.add(ColumnSchemaVO.builder()
						.tableName(rs.getString("TABLE_NAME"))
						.columnName(rs.getString("COLUMN_NAME"))
						.dataType(rs.getString("DATA_TYPE"))
						.dataLength(rs.getInt("DATA_LENGTH"))
						.nullable(rs.getString("NULLABLE"))
						.build()
						);
//				vo.setTableName(rs.getString("TABLE_NAME"));
//				vo.setColumnName(rs.getString("COLUMN_NAME"));
//				vo.setDataType(rs.getString("DATA_TYPE"));
//				vo.setDataLength(rs.getInt("DATA_LENGTH"));
//				vo.setNullable(rs.getString("NULLABLE"));
			}

			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
