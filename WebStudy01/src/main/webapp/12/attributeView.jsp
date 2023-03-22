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
<pre>
	<%=pageContext.getAttribute("pageAttr") %>
	<%=pageContext.getAttribute("requestAttr", PageContext.REQUEST_SCOPE)%>
	<%=session.getAttribute("sessionAttr") %>
	<%=application.getAttribute("applicationAttr") %>
	<%
		session.removeAttribute("sessionAttr"); // flush attribute 방식
	%>
</pre>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>