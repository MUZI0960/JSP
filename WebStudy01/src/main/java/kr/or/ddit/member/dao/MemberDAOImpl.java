package kr.or.ddit.member.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.utils.CaseConvertingUtils;
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
		    		saved = simpleDataMapper(rs, MemberVO.class);
//		    		saved = new MemberVO();
//		    		saved.setMemId(rs.getString("MEM_ID"));
//		    		saved.setMemPass(rs.getString("MEM_PASS"));
//		    		saved.setMemName(rs.getString("MEM_NAME"));
		    	}
		         return saved;  
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	private <T> T simpleDataMapper(ResultSet rs, Class<T> resultClass) throws SQLException{
		try {
			T object = resultClass.newInstance();
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			String[] columnNames = new String[count];
			String[] propertyNames = new String[count];
			for(int idx=1; idx<=count; idx++) {
				columnNames[idx-1] = rsmd.getColumnName(idx);
				propertyNames[idx-1] = CaseConvertingUtils.snakeToCamel(columnNames[idx-1]);
			}
			
			for(int idx=0; idx<count; idx++) {
				String columnName = columnNames[idx];
				String propertyName = propertyNames[idx];
				PropertyDescriptor pd = new PropertyDescriptor(propertyName, resultClass);
				Class<?> propertyType = pd.getPropertyType();
				Method setter = pd.getWriteMethod();
				// member.setMemId(rs.getString("MEM_ID"));
				Object columnValue = null;
				if(propertyType.equals(Integer.class)) {
					columnValue = rs.getInt(columnName);
				}else {
					columnValue = rs.getString(columnName);
				}
				setter.invoke(object, columnValue);
			}
			return object;
		}catch (Exception e) {
			throw new SQLException(e);
		}
	}
	
	@Override
	public List<MemberVO> selectMemberList() {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" SELECT MEM_ID, MEM_NAME, TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,"); 
        sql.append(" MEM_HP, MEM_MAIL,  MEM_ADD1,                                    ");
        sql.append(" TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,         ");
        sql.append(" MEM_MILEAGE                                                     ");
		sql.append(" FROM MEMBER                                                     ");
		
		List<MemberVO> list = new ArrayList<>();
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(simpleDataMapper(rs, MemberVO.class));
//				list.add(
//						MemberVO.builder()
//								.memId(rs.getString("MEM_ID"))
//								.memName(rs.getString("MEM_NAME"))
//								.memBir(rs.getString("MEM_BIR"))
//								.memHp(rs.getString("MEM_HP"))
//								.memMail(rs.getString("MEM_MAIL"))
//								.memAdd1(rs.getString("MEM_ADD1"))
//								.memMemorialday(rs.getString("MEM_MEMORIALDAY"))
//								.memMileage(rs.getInt("MEM_MILEAGE"))
//								.build()
//					);
			}
			return list;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		StringBuffer sql = new StringBuffer();
		
	      sql.append("   SELECT                                                                ");
	      sql.append("       MEM_ID, MEM_PASS, MEM_NAME,                                       ");
	      sql.append("       MEM_REGNO1, MEM_REGNO2, TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,          ");
	      sql.append("       MEM_ZIP, MEM_ADD1, MEM_ADD2,                                       ");
	      sql.append("       MEM_HOMETEL, MEM_COMTEL, MEM_HP,                                   ");
	      sql.append("       MEM_MAIL, MEM_JOB, MEM_LIKE,                                       ");
	      sql.append("       MEM_MEMORIAL, TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,     ");
	      sql.append("       MEM_MILEAGE, MEM_DELETE                                           ");
	      sql.append("   FROM MEMBER                                                           ");
	       sql.append("   WHERE MEM_ID = ?                                                      ");       
		try(
				Connection conn = ConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, memId);
			ResultSet rs = pstmt.executeQuery();
			MemberVO member = null;
			while(rs.next()) {
				member = simpleDataMapper(rs, MemberVO.class);
			}
			
			return member;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
