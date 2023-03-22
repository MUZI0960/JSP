<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.3.min.js"></script>
</head>
<body>
<h4>Http Request packaging</h4>
<form method="post">
	<input type="hidden" name="_method" value=delete/>
</form>
<pre>
	Request Line : protocol/version, URL(URI, 자원에 대한 식별자, 명사), http Method(자원에 대한 행위, 동사)
		
		<%=request.getRequestURL() %>
		<%=request.getRequestURI() %>
		
		<%=request.getMethod() %>
		
		http(request) Method : 요청의 의도(목적)와 패키징 방식을 표현.
		GET(R) : default method, 서버의 자원 조회, body 영역이 형성되지 않음.
		POST(C) : 클라이언트의 의도적 전송 컨텐츠가 존재함. body 영역이 형성됨. content-* 종류의 요청 헤더들이 존재함.
		PUT/PATCH(U)
		DELETE(D)
		OPTION : preFlight 요청으로 본 요청의 메소드 지원 여부 파악
		HEAD : response body가 없는 형태로 응답이 전송됨.
		TRACE : server debugging 용도로 제한적 사용.
		
		ex) /member/memberList.do
			/member/memberView.do?memId=a001
			/member/memberInsert.do
			/member/memberUpdate.do
			/member/memberDelete.do
			
			RESTful URI 구조
			/member (GET)
			/member/a001 (GET)
			/member (POST)
			/member/a001 (PUT)
			/member/a001 (DELETE)
			/member (DELETE)
			
	Request Header : 클라이언트와 요청을 수식하기 위한 비의도적 메타데이터.
		<%=request.getHeader("Accept") %>
		
		name/value 쌍의 문자열 형태로 전송
		ex) Accept-* : 응답 컨텐츠의 속성을 제한하는 요청 헤더.
			Content-* : request body 를 통해 전송되는 컨텐츠의 속성을 표현하는 헤더.
				Content-Type : application/xml
				Content-Length : 12222
			User-Agent : 클라이언트가 사용하는 시스템에 대한 정보를 표현하는 헤더.
			ex) Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36
							  현재 클라이언트 os정보				렌더링 엔진 (HTML 번역 담당) 	
				Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36 Edg/110.0.1587.57
	Request Body(content body, Message Body)
		: 클라이언트가 전송하는 의도적 입력 데이터(컨텐츠) 영역,
		: Content-Type 요청 헤더에 따라 컨텐츠 형식이 다를 수 있음.
		ex) Content-Type : application/x-www-form-urlencoded -> 파라미터로 전송된 문자열
			Content-Type : multipart/form-data -> 파트 형태로 전송된 컨텐츠
			Content-Type : application/json -> JSON 형태로 전송된 컨텐츠
	
===> HttpServletRequest 객체의 형태로 캡슐화 됨.
		
</pre>
<form method="post" enctype="multipart/form-data">
	<input type="text" name="param1" value="한글" />
	<input type="number" name="param2" value="1024" />
	<input type="submit" value="전송">
</form>
<script type="text/javascript">
	$.ajax({
		method : "post" 
		, contentType : "application/json"
		, data : JSON.stringify({
			param1 : "한글"
			, param2 : 1024
		})
		, dataType : "json"
	}).done(function() {
		alert("성공");
	}).fail(function() {
		console.log("ERROR !!! ");
	})
</script>
</body>
</html>
















