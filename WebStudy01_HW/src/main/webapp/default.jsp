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
<%
	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	if(authMember!=null){
		%>
		<%=authMember.getMemName() %>님 
		<form name="logoutForm" method="post" action="<%=request.getContextPath() %>/login/logout"></form>
		<a href="javascript:;" onclick="document.logoutForm.submit();">로그아웃</a>
		<%
	}else{
		%>
		<a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인</a>
		<%
	}
%>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>