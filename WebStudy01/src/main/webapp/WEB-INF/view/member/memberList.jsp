<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>

<body>

<table class="table table-bordered">
   <thead>
      <tr>
         <th>회원아이디</th>
         <th>회원명</th>
         <th>생일</th>
         <th>이메일</th>
         <th>휴대폰</th>
         <th>주소1</th>
         <th>기념일</th>
         <th>마일리지</th>
      </tr>
   </thead>
   <tbody>
   	<!--! isEmpty -->
      <c:if test="${not empty list }">
      	<c:forEach items="${list }" var="member">
      	<c:url value="/member/memberView.do" var="viewURL">
      		<c:param name="who" value="${member.memId}"></c:param>
      	</c:url>
      		<tr>
      			<td>${member.memId}</td>
      			<td><a href="${viewURL }">${member.memName}</a></td>
      			<td>${member.memBir}</td>
      			<td>${member.memMail}</td>
      			<td>${member.memHp}</td>
      			<td>${member.memAdd1}</td>
      			<td>${member.memMemorialday}</td>
      			<td>${member.memMileage}</td>
      		</tr>
      	</c:forEach>
      </c:if>
      <c:if test="${empty list }">
      	<tr>
      		<td colspan="8">회원이 없음</td>
      	</tr>
      </c:if>
   </tbody>
</table>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>