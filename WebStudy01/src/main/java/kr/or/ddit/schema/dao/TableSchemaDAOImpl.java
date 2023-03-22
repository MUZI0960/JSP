package kr.or.ddit.schema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.TableSchemaVO;

public class TableSchemaDAOImpl implements TableSchemaDAO {

	@Override
	public List<TableSchemaVO> selectTableSchemaList() {
		StringBuffer sql = new StringBuffer();
		// 1. 쿼리 결정
		 sql.append(" SELECT TABLE_NAME, TABLESPACE_NAME, NUM_ROWS ");
		 sql.append(" FROM USER_TABLES                              ");
		 List<TableSchemaVO> list = new ArrayList<>();
		try (
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			// 2. 쿼리 파라미터 설정
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 3. 결과집합 -> POJO(VO)
				TableSchemaVO vo = TableSchemaVO.builder()
									.tableName(rs.getString("TABLE_NAME"))
									.tablespaceName(rs.getString("TABLESPACE_NAME"))
									.numRows(rs.getInt("NUM_ROWS"))
									.build();
				list.add(vo);
			}

			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
