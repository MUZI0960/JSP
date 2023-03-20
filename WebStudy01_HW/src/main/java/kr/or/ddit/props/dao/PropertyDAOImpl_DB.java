package kr.or.ddit.props.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.props.vo.PropertyVO;

public class PropertyDAOImpl_DB implements PropertyDAO{

	@Override
	public List<PropertyVO> selectProperties(String propertyName) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION ");
		sql.append(" from database_properties ");
		if(StringUtils.isNotBlank(propertyName)) {
			sql.append(" where PROPERTY_NAME = ? ");
		}

		List<PropertyVO> list = new ArrayList<PropertyVO>();
	      try (
	    		Connection conn = ConnectionFactory.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	      ){
		      if(StringUtils.isNoneBlank(propertyName)) {
		    	  pstmt.setString(1, propertyName);
		      }
	    	  
	    	  ResultSet rs = pstmt.executeQuery();
	    	  while (rs.next()) {
		           PropertyVO vo = new PropertyVO();
		           vo.setPropertyName(rs.getString("PROPERTY_NAME"));
		           vo.setPropertyValue(rs.getString("PROPERTY_VALUE"));
		           vo.setDescription(rs.getString("DESCRIPTION"));
		           list.add(vo);
	         }
	         return list;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertProperty(PropertyVO propertyVO) {
		throw new RuntimeException("뷰를 대상으로 한 insert는 불가능");
	}

	@Override
	public int updateProperty(PropertyVO prop) {
		throw new RuntimeException("뷰를 대상으로 한 update는 불가능");
	}

	@Override
	public int deleteProperty(PropertyVO prop) {
		throw new RuntimeException("뷰를 대상으로 한 delete는 불가능");
	}

}
