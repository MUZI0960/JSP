<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="10kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" flush="false"/>
</head>
<body>
<h4>JspWriter을 이용한 버퍼 관리</h4>
<pre>
	1. 응답 데이터를 버퍼에 기록, append, write, print ...
	2. 버퍼의 상태를 확인
		buffer size : <%=out.getBufferSize() %>, 잔여 버퍼 사이즈 : <%=out.getRemaining() %>
	3. 버퍼의 내용을 방출 : 한번 flush 된 이후에는 예외나 에러페이지로 전환이 불가능함.
		<%-- 
			out.flush();
			if(1==1){
				throw new RuntimeException("강제 발생 예외");
			}
		--%>
	4. 버퍼의 내용을 삭제
		<%--
			out.clearBuffer();
		--%>
		<%
			for(int count = 1; count <=100; count++){
				out.println(String.format("%d 번째 반복문 기록", count));
				if(count==90) out.flush();
				if(count==99){
					throw new RuntimeException("강제 발생 예외");
				}	
			}
		%>
</pre>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>