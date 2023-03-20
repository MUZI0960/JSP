<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 응답 헤더를 이용한 캐시 제어 </h4>
<pre>
	Pragma(http 1.0), Cache-Control(http 1.1), Expires(all version)
	
	Pragma(http 1.0), Cache-Control(http 1.1)
		- public
		- private  
		- no-cache - 캐싱하지 않는다. 저장된 캐시가 있다면 사용전에 만료여부를 항상 확인한다.
		- no-store - 캐싱하지 않는다.
		- must-vaildate 
		- max-ages : milliseconds (캐시 저장 기간 설정)
		<%
			response.setHeader("Pragma", "no-cache");
			response.addHeader("Pragma", "no-store");
			
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Cache-Control", "no-store");
			
			response.setDateHeader("Expires", 0); // 캐시 남기지 마라
		%>
		웹 표준화 전략 -> 모든 방식 사용해서 모든 클라이언트가 접근 가능 하도록
</pre>
</body>
</html>