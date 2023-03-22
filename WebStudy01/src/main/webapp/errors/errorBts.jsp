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
<h1>BTS 멤버 못찾았어</h1>
<h1><%=exception.getMessage() %></h1>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>