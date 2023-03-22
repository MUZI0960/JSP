<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.printf("파라미터 : %s\n", request.getParameter("param"));
	String location = request.getContextPath() + "/08/dest.jsp";
// 	301
// 	302
// 	307
// 	response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
// 	response.setStatus(HttpServletResponse.SC_FOUND);
	// 이전 요청의 메서드 복사해서 보낸다.
	// put 메서드 사용 시 주로 사용
	response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
	
	response.setHeader("Location", location);
%>
