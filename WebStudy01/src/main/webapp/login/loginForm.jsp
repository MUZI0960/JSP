<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<c:if test="${not empty message }">
	<script>
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/>
</c:if>

</head>
<body>
<form action="${pageContext.request.contextPath }/login/loginProcess" method="post">
	<ul>
		<li>아이디 : <input type="text" name="memId" /></li>
		<li>비밀번호 : <input type="password" name="memPass" /></li>
		<il>
			<input type="submit" value="로그인" />
		</il>
	</ul>
</form>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>