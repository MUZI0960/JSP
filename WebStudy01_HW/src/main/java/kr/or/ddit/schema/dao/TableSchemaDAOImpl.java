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
		String sql = " SELECT TABLE_NAME, TABLESPACE_NAME, NUM_ROWS FROM USER_TABLES ";
		
		
		List<TableSchemaVO> list  = new ArrayList<TableSchemaVO>();
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TableSchemaVO vo = new TableSchemaVO();
				vo.setTableName(rs.getString("TABLE_NAME"));
				vo.setTablespaceName(rs.getString("TABLESPACE_NAME"));
				vo.setNumRows(rs.getInt("NUM_ROWS"));
				list.add(vo);
			}
			return list;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
