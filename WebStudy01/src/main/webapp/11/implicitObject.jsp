<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>JSP 스펙에서 제공되는 기본 객체</h4>
<pre>
	request(HttpServletRequest)
	response(HttpServletResponse)
	out(JspWriter) : <a href="bufferDesc.jsp">bufferDesc.jsp 참고</a>
	session(HttpSession) : <a href="sessionDesc.jsp">sessionDesc.jsp 참고</a>
	application(ServletContext) :  <a href="applicationDesc.jsp">applicationDesc.jsp 참고</a>
	
	page(Object) == this (현재 등록된 객체의 정보 필요 시) : 커스텀 태그 개발에 활용. 
	커스텀 태그 사용 형태 : <!-- <prefix:tagname attributes ... > -->
	config(ServletConfig) : 동록된 서블릿의 메타데이터를 가진 객체.
	
	exception(Throwable) : 예외 처리 목적으로 활용. isErrorPage="true"
	
<!-- 	CAC(Context Aware Computing) -->
	**** pageContext(PageContext) : 현재 JSP 페이지에 대한 모든 정보를 가진 객체,
									가장 먼저 생성되고, 나머지 모든 기본 객체의 참조를 소유함.
									<a href="pageContextDesc.jsp">pageContextDessc.jsp</a>
</pre>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>