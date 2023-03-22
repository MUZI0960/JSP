<%@page import="java.text.Format"%>
<%@page import="kr.or.ddit.vo.ColumnSchemaVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.schema.service.SchemaServiceImpl"%>
<%@page import="kr.or.ddit.schema.service.SchemaService"%>
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
<h4>Performance Check</h4>
<pre>
	공간 : 메모리 소요량
			ex) concat 연산자 대신 formating 활용할 것.
			-> method area(constant pool) 대신 heap area 사용하라.
			String txt = "TEXT";
			txt += "append";
			"TEXT", "append", "TEXTappend" -> method area(constant pool)에 세 개의 메모리 공간 점유함.
			
			StringBuffer txt = new StringBuffer("TEXT");
			txt.append("append");
			txt = null;
	시간 : response time = processing time + latency time
	<a href="oneConnOneProcess.jsp">한번 연결 한번 처리 : 15ms, 1ms </a>
	<a href="100Conn100Process.jsp">100번 연결 100번 처리 : 1100ms, 50ms </a>
	<a href="OneConn100Process.jsp">한번 연결 100번 처리 : 40ms, 2ms</a>
</pre>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>