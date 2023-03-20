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
<%
	String content = (String)request.getAttribute("content");
%>

<jsp:include page="<%=content %>" />
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>