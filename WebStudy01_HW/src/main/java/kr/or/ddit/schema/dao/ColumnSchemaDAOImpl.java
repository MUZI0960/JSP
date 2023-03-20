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

	@Override
	public List<ColumnSchemaVO> selectColumnSchemaListByTableName(String tableName) {
		String sql = "SELECT TABLE_NAME, COLUMN_NAME, DATA_TYPE FROM COLS WHERE TABLE_NAME = ?";
		
		List<ColumnSchemaVO> list = new ArrayList<ColumnSchemaVO>();
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			
			pstmt.setString(1, tableName);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ColumnSchemaVO vo = new ColumnSchemaVO();
				vo.setTableName(rs.getString("TABLE_NAME"));
				vo.setColumnName(rs.getString("COLUMN_NAME"));
				vo.setDataType(rs.getString("DATA_TYPE"));
				list.add(vo);
			}
			return list;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

}
