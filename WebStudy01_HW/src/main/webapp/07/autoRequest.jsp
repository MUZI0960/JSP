<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- http-equiv : content 속성에 명시된 값에 대한 HTTP 헤더를 제공 -->
<!-- refresh : 해당 문서를 새로고침(refresh)할 시간 간격을 명시함 -->
<meta http-equiv="Refresh" content="5;url=https://www.naver.com">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.3.min.js"></script>
</head>
<body>
<h4>현재 서버의 시간 : <span id="timeArea"></span></h4>
<h4> 주거적인 자동 요청 발생 </h4>
<pre>
1. server side : response header (Refresh) <%-- response.setIntHeader("Refresh", 1); --%>
2. client side
	- JS : scheduling function (setTimeout, setInterval)
	- HTML : meta
</pre>
<textarea rows="5" cols="100"></textarea>
<script type="text/javascript">
	let timeArea = $('#timeArea');
	setInterval(function(){
		$.ajax({
			url : "<%=request.getContextPath()%>/07/getServerTime.jsp",
			dataType : "html"
		}).done(function(resp, textStatus, jqXHR) {
			timeArea.html(resp);
		}).fail(function(jqXHR, status, error) {
			console.log(`상태코드 : \${status}, 에러메세지 : \${error}`);
		});
	}, 1000);
</script>
</body>
</html>