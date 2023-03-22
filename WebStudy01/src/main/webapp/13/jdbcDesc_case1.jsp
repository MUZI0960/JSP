<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.props.vo.PropertyVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DatabaseMetaData"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity) API - java.sql</h4>
<pre>
	1. driver를 현재 빌드패스에 추가 : 3x) %catalina_home%\lib\ojdbc6.jar
	2. driver class loading
	==>ConncetionFactoy -> static code block
	3. Connection 수립
	<%
		List<PropertyVO> list = null;
		try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();	
		){
			DatabaseMetaData metaData = conn.getMetaData();
			out.println(metaData.getDriverVersion());
	%>
	4. 쿼리 객체 생성(Statement) : sql 컴파일과 실행 담당.
		Statement : 동적 쿼리 실행에 적합한 기본 쿼리 객체
		PreparedStatement : 선컴파일된 쿼리 객체. 정적 쿼리, literal 을 쿼리 파라미터(?) 형태로 변경할 수 있음.
		CallableStatement : function 이나 procedure 처럼 일련의 명령 집합을 호출 형태로 실행할 수 있음.
	<%
			StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION"); 
			sql.append(" FROM DATABASE_PROPERTIES");
		
	%>
	5. 쿼리 실행 
		SELECT : ResultSet excuteQuery()
		INSERT, UPDATE, DELETE : int excuteUpdate() 
	<%
			ResultSet rs = stmt.executeQuery(sql.toString());
	%>	
	6. 결과 집합 핸들링
	<%
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next()){
				PropertyVO vo = new PropertyVO();
				if(list == null) list = new ArrayList<>();
				vo.setPropertyName(rs.getObject("PROPERTY_NAME").toString());
				vo.setPropertyValue(rs.getObject("PROPERTY_VALUE").toString());
				vo.setDescription(rs.getObject("DESCRIPTION").toString());
				list.add(vo);
			}
	%>
	7. 모든 자원 해제
	<%
		}catch(SQLException e){
			throw new RuntimeException(e);
		} 
	%>		
</pre>
<table>
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody>
		<%
			StringBuffer sb = new StringBuffer();
			String ptrn = "<td>%s</td>";
			for(int i = 0; i<list.size(); i++){
				sb.append("<tr>");
				PropertyVO vo = new PropertyVO();
				String name = vo.getPropertyName();
				String value = vo.getPropertyValue();
				String dc = vo.getDescription();
				
				sb.append(String.format(ptrn,name));
				sb.append(String.format(ptrn,value));
				sb.append(String.format(ptrn,dc));
			}
		%>
	</tbody>
	
</table>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>
	<%--
// 		1. MemberVO 클래스를 method area(상수 메모리) 에 적재(loading) : ClassLoader
				static code block 실행
// 		2. 1번에서 로딩된 클래스를 바탕으로 인스턴스를 생성(생성자), heap area에 적재
// 		3. vo 변수에 2번의 메모리 참조 주소(reference)를 할당
		MemberVO vo = new MemberVO();
		MemberVO vo2 = new MemberVO();
	--%>
	
	
	
	
	