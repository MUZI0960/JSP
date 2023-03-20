<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>웹 어플리케이션에서 흐름 제어(A->B) 방법</h4>
<pre>
	1. Request Dispatch (요청 분기)
		- Forward : 요청은 A(서블릿)로 응답은 B(JSP 활용케이스가 많음.)에서만 처리됨(Model2 구조에서 활용). 서버사이드 위임 처리 방식.
			
		<%
			String path = "/04/standard.jsp";
// 			RequestDispatcher rd = request.getRequestDispatcher(path);
// 			rd.forward(request, response);
// 			rd.include(request, response);
		%>	
<%-- 		<jsp:include page="<%=path %>"/>			 --%>
		- Include : 요청은 A로 전송 -> 서버 내에서 B로 이동 -> A로 복귀
					(최종 응답은 A+B 형태로 전송, A가 B를 내포함.)=>페이지 모듈화에서 활용.
	2. Redirect 
			요청은 A로 전송 -> Body가 없고, 상태 코드가 3xx 인 응답 전송(Location 헤더 포함) ==> connetLess, stateLess
			-> Location(B) 방향으로 새로운 요청 전송 -> B에서 최종 응답 전송.
		301/302 : 원본 요청(A)에 대한 정보가 삭제된 후, GET방식의 새로운 요청인 redirection.
					POST -> redirect -> GET ==> PRG 패턴 처리 구조.	(ex. 로그인 처리)	
		307 : REST 처리에서 많이 활용되며, PUT/DELETE 요청에서 활용.
				원본 요청이 발생하고, redirection 될 때, 원본의 method와 body를 복사해서 새로운 요청이 발생함.
				
			<%-- 
				//클라이언트가 사용 -> getContextPath로 절대경로 필요
				response.sendRedirect(request.getContextPath() + path);
			--%> 
</pre>
</body>
</html>