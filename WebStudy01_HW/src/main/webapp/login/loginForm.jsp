<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<%
	String message = (String) session.getAttribute("message");
	session.removeAttribute("message"); // flush attribute
	if(StringUtils.isNoneBlank(message)){
		%>
			<script type="text/javascript">
// 				Swal.fire({
// 					  icon: 'error',
// 					  title: 'Oops...',
<%-- 					  text: '<%=message%>', --%>
// 					});
				alert("<%=message%>");
			</script>
		<%
	}
%>
</head>
<body>
<form action="<%=request.getContextPath() %>/login/loginProcess" method="post">
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