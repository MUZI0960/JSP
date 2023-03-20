<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 요청 헤더의 종류 </h4>
<table>
<thead>
	<tr>
		<th>헤더이름</th>
		<th>헤더값</th>
	</tr>
</thead>
<tbody>
<%
	String trPtrn = "<tr><th>%s</th><td>%s</td></tr>";
	Enumeration<String> en = request.getHeaderNames();
	String headerName;
	while(en.hasMoreElements()){
		headerName = en.nextElement();
		String headerValue =request.getHeader(headerName);
		out.println(
			String.format(trPtrn, headerName, headerValue )	
		);
	}
%>
</tbody>
</table>
</body>
</html>