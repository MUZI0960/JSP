<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<style type="text/css">
.green{
	background-color: green;
}
.red{
	background-color: red;
}
/* 첫 마지막 빨간색 */
</style>
</head>
<body>
<table class='table table-bordered'>
	
<c:forEach var="dan" begin="2" step="2" end="9" varStatus="lts">
	<c:choose>
		<c:when test="${lts.count eq 3}">
			<c:set var="clzValue" value="green"/>
		</c:when>
		<c:when test="${lts.first or lts.last}">
			<c:set var="clzValue" value="red"/>
		</c:when>
		<c:otherwise>
			<c:set var="clzValue" value="normal"/>
		</c:otherwise>
	</c:choose>
		<tr class="${clzValue}">
			<c:forEach var="mul" begin="1" end="9">
				<td>${dan }*${mul }= ${dan*mul }</td>
			</c:forEach>
		</tr>
	</c:forEach>
</table>


<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>