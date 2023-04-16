<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<form:form method="post" modelAttribute="regist">
	<ul>
		<li>
			EMAIL : <form:input path="email" />
			<form:errors path="email" element="span" class="text-error" />
		</li>
		<li>
			PASSWORD : <form:password path="password"/> 
			<form:errors path="password" element="span" class="text-error" />
		</li>
		<li>
			NAME : <form:input path="name"/>
			<form:errors path="name" element="span" class="text-error"/> 
		<li>
			NICKNAME : <form:input path="nickName"/>
			<form:errors path="nickName" element="span" class="text-error"/> 
		<li>
			<form:button type="submit">전송</form:button>
		</li>
	</ul>
</form:form>	
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>














