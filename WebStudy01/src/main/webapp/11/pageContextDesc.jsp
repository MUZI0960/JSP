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
<h4>PageContext</h4>
<pre>
	: 현재 JSP 페이지의 모든 정보(나머지 기본객체 참조)를 가진 객체.
	
	1. 기본 객체중에서 가장 먼저 생성
	2. 나머지 기본객체 참조 <%=request == pageContext.getRequest() %>
	3. 흐름제어 (foward, include)
	4. 예외 정보 확보 (exception)
	5. 4개의 scope 접근 가능.
	<!-- *주소 비교 -->
	<%=request==pageContext.getRequest() %>
	<!-- 상태값 비교 -->
	<%=request.equals(pageContext.getRequest()) %>
	
	<%
		pageContext.include("/04/standard.jsp");
// 		request.getRequestDispatcher("/04/standard.jsp").include(request, response);
	%>
	======================
</pre>

<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>