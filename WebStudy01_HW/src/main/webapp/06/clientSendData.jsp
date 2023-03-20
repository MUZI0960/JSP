<%@page import="java.io.InputStream"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 파라미터 확보 전 사용함.
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>파라미터 : <%=Arrays.toString(request.getParameterValues("param1")) %></h4>
<h4> 클라이언트 전송 컨텐츠 확보 </h4>
<pre>
	클라이언트의 의도
	적 전송 데이터 분류
	1. request parameter(문자열, urlEncoded[percent encoded] string) 
			: String getParameter(name), Map&lt;String, String[]&gt; getParameterMap(), 
			  String[] getParameterValues(name), Enumeration&lt;String&gt; getParameterNames()
			  (key가 동일한 value가 있을 수 있기 때문에 배열 / Map 형식으로 관리됨)
		- line 을 통한 query string
		- body 을 통한 form data
		
	2. multi part : Part(servlet 3.x 부터) 타입으로 하나의 part 데이터를 캡슐화함.
			getPart, getParts
			(
			body part를 여러개로 쪼갠다, part의 기반은 form
			1. 문자기반/2. 파일기반
			)
	3. content payload : body 영역을 통해 특정 컨텐츠 타입(Content-Type)으로 전송되는 경우.
		역직렬화 -> 언마샬링 구조 필요(ObjectMapper), request.getInputStream()
		<%--
			InputStream is = request.getInputStream();
		--%>
	(2 3 ->body 있어야함)
</pre>
<form action="./fileUploadProcess" method="post" enctype="multipart/form-data">
	<input type="text" name="param1" value="한글값1"/>
	<input type="text" name="param1" value="value2"/>
	<input type="file" name="uploadFile">
	<input type="submit" value="전송">
</form>
</body>
</html>