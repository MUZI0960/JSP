package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.props.vo.PropertyVO;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public MemberVO selectMemberForAuth(String memId) {
		// 1.
		String sql = " SELECT MEM_ID, MEM_PASS, MEM_NAME FROM MEMBER WHERE MEM_ID = ?";
		MemberVO saved = null;
		try(
				Connection conn =  ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			){
		    	// 2.
				pstmt.setString(1, memId);
		    	
		    	ResultSet rs = pstmt.executeQuery();
		    	if(rs.next()) {
		    		// 3.
		    		saved = new MemberVO();
		    		saved.setMemId(rs.getString("MEM_ID"));
		    		saved.setMemPass(rs.getString("MEM_PASS"));
		    		saved.setMemName(rs.getString("MEM_NAME"));
		    	}
		         return saved;  
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
