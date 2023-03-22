<%@page import="java.io.IOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! 
String ptrn = "<span id='spanId_%1$d'>%1$d</span>\n";
private String gerateSpan(int num){
	
	return String.format(ptrn, num);
}
private void gerateSpan(int num, JspWriter out) throws IOException{
	out.println(String.format(ptrn, num));
}
%>
<%
	
	for(int i = 1; i<=10; i++){
		out.print(String.format(ptrn, i));
	}
	for(int i = 1; i<=10; i++){
		out.println(gerateSpan(i));
	}
	for(int i = 1; i<=10; i++){
		gerateSpan(i, out);
	}
%>
</body>
</html>