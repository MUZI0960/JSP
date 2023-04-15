<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<c:set var="authMember" value="${sessionScope.authMember }"/>
<c:if test="${not empty authMember }">
	<form name="logoutForm" method="post" action="<c:url value='/login/logout'/>"></form>
	<a href="<c:url value='/mypage.do'/>">${authMember.memName }</a> 
	<a href="javascript:;" onclick="document.logoutForm.submit();">로그아웃</a>
</c:if>
<c:if test="${empty authMember }">
	<a href="${pageContext.request.contextPath }/login/loginForm.jsp">로그인</a>
	<a href="<c:url value='/member/memberInsert.do'/>">회원가입</a>
</c:if>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>