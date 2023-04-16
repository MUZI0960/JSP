<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<c:set var="principal" value="${pageContext.request.userPrincipal }"/>
<c:if test="${not empty principal }">
	<c:set var="authMember" value="${principal.realUser }"/>
	<form name="logoutForm" method="post" action="<c:url value='/login/logout'/>"></form>
	<a href="<c:url value='/mypage.do'/>">${authMember.memName }[${authMember.memRole }]</a> 
	<a href="javascript:;" onclick="document.logoutForm.submit();">로그아웃</a>
</c:if>
<c:if test="${empty principal }">
	<a href="${pageContext.request.contextPath }/login/loginForm">로그인</a>
	<a href="<c:url value='/member/memberInsert.do'/>">회원가입</a>
</c:if>








