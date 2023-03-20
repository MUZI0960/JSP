<%@page import="java.nio.file.Files"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	img{
		width:  200px;
		height: 200px;
	}
</style>
</head>
<body>
<h4>자원의 종류와 식별 방법</h4>
<pre>
	: 자원의 위치와 식별(접근, 경로) 방법에 따른 분류
	1. file system resource : 파일시스템 상에 존재하는 파일 형태, 물리 경로를 통한 직접 접근.
	ex) D:\00.medias\images\cat1.jpg
	2. class path resource : class path 상에 존재하는 자원
	ex) /images/cat1.jpg : class path 기준의 절대 경로(qualified name)로 접근 (논리 경로)
	ex) kr.or.ddit.servlet01.DescriptionServlet == kr/or/ddit/servlet01/DescriptionServlet
	ex) /kr/or/ddit/sample.properties
	3. web resource : web 상에 존재하는 자원, URL/URI 형태의 주소를 통한 접근.
	ex) https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
	ex) /resources/images/cat1.jpg
	ex) /WebStudy01/resources/images/cat1.jpg
	ex) ../resources/images/cat1.jpg
	
	URL(URI) 표기 방식
	절대 경로
		protocol://ip[dn][:port]/context/depth../resourcename
		CLIENT SIDE : /WebStudy01/resources/images/cat1.jpg
			-> window.location 객체의 모든 프로퍼티는 생략 가능.
		SERVER SIDE : /resources/images/cat1.jpg
		<%=application.getContextPath() %>
		<%
// 			String file = application.getResource("/resources/images/cat1.jpg").getFile();
			String file = application.getResource("/resources/images/cat1.jpg").getFile();
			out.println(file);
		%>
	상대 경로 : 현재 위치를 기준으로 한 상대적 경로 표헌.
		client side : window.location.href를 기주으로 경로 표현.
	
	ex) /image/imageForm.do
	URI (Uniform Resource Identifier)
		URL (Uniform Resources Locator)
		URN (Uniform Resources Naming)
		URC (Uniform Resources Contents)
	<%
		String srcURL = "/resources/images/cat3.png";
		String destURL = "/05/cat3.png";
// 		Files.copy();
	%>
	
</pre>	
<img src="http://localhost/WebStudy01/resources/images/cat1.jpg"/>
<img src="/WebStudy01/resources/images/cat1.jpg"/>
<img src="../resources/images/cat1.jpg"/>
<img src="<%=application.getContextPath() %>/05/cat3.png">
</body>
</html>



