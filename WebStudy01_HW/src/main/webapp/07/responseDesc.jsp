<%@ page language="java" pageEncoding="UTF-8"%>
<%
// 	response.setContentType("text/plain;charset=UTF-8");
// 	response.setContentLength(100);
	response.setHeader("content-Type", "text/plain;charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Http(Stateless, Connectless) response packaging</h4>
<pre>
	: HttpServletResponse(응답과 관련된 모든 정보를 가진 객체) 활용
	
	1. response Line : 요청 처리 결과를 표현하는 상태코드 (status code)
		ex) sendError(sc), setStatus(sc)
		status code
		100~ : ING... webSocket : Http의 하위 프로토콜 형태로 connectful 구조를 가짐. 
		200~ : OK(success)
		300~ : 클라이언트의 다음 액션에 대한 유도. body가 없이 line+header 로만 응답이 구성됨.
			ex) 
			304 : Not Modified
			301(영구적)/302(임시)/307(임시) : Moved + Location 헤더와 병용.
		
		400~ : client side failure
			400 : Bad Request, 요청 데이터 검증에 활용.
			404 : Not Found, 자원X 제공할 수 있는 서비스 X
			405 : Method Not Allowed
			401/403 : Authentication(인증) / Authorization(인가) 기반의 접근제어에 활용.
			406/415 : 요청이나 응답의 컨텐츠 타입과 관련하여 활용.
					406 : Not Acceptable - accept request header 에 있는 MIME 컨텐츠를 생성할 수 없음.
					415 : UnSupported Media Type - request body content를 해석할 수 없음.
		500~ : server side failure, 500(Internal server error)
	2. response Header : response content에 대한 meta data, Content-*
		ex) setHeader(name, value), setIntHeader(name, intValue), setDateHeader(name, longValue)
			addHeader(name, value)
		1) response body content에 대한 설정 : Content-*
		2) 캐시 제어를 위한 설정 : Cache-control
			<a href="cacheControl.jsp">캐시 제어</a>
		3) auto request : Refresh
			<a href="autoRequest.jsp">auto request</a>
		4) flow control : Location
	3. response Body[Content-body, Message-Body] : 클라이언트가 요청한 컨텐츠 자체.
</pre>
</body>
</html>









