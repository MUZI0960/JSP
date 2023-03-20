<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>HttpSession</h4>
<h4>세션 타이머 : <span id="timerArea" data-timeout="<%=session.getMaxInactiveInterval()%>"
									data-url="<%=request.getContextPath()%>/11/sessionExtend"
					></span></h4>

<h4 id="timerArea2" data-timeout="360"></h4>
<pre>
	세션 : 
		시간의 개념 : 사용자가 어플리케이션을 사용하기 시작하는 순간부터 사용종료 까지의 시간.
		통로의 개념 : 클라이언트와 서버 사이의 수립된 유일한 연결(connection).
		
	세션의 생명 주기
		시작 : 해당 사용자로부터 최조의 요청이 발생했을 때 -> 세션 아이디 부여 -> 응답데이터로 사용자에게 전송.
				사용자는 timeout 이내에 새로운 요청을 발생(저장되어있던 세션아이디를 첨부)
		종료	: 
			명시적인 로그아웃, 
			timeout(만료 시간)의 개념을 도입.
				-> timeout 이내에 새로운 요청이 발생하지 않을 때
				-> 브라우저를 종료하는 등의 쿠키 트래킹 모드 데이터(ex.쿠키)가 삭제되는 경우.
				-> 쿠키와 같은 트래킹 모드 데이터를 직접 삭제하는 경우.
	
	세션의 식별 속성
		세션 아이디 : <%=session.getId() %>	 
	세션의 기타 속성
		세션 생성 시점 : <%=new Date(session.getCreationTime()) %>
		마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %>
		timeout : <%=session.getMaxInactiveInterval() %>
	세션 트래킹 모드 
		1. COOKIE : JSESSIONID 와 같은 쿠키의 형태로 C/S 상이의 세션아이디 전송.
		2. URL : <a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">세션 유지</a>
		3. SSL : C/S 사이에 전송되는 세션 아이디를 암호화
</pre>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/packages/11/sessionTimer.js"></script>
<script type="text/javascript">
// let timerArea = $("#timerArea");
// function time(){
<%-- 	let creationTime = <%=(new Date(session.getLastAccessedTime()).getTime() - new Date(session.getCreationTime()).getTime())/ 1000 %> --%>
// 	let creationTimeMin = Math.floor(creationTime/60);
// 	let creationTimeSec = creationTime%60;
// 	let sessionTime = (creationTimeMin+":"+creationTimeSec);
	
// 	timerArea.text(sessionTime);
// }
// setInterval(time, 1000);
	
// 	console.log(`\${timerInfo.timerArea}\ntimeout : \${timerInfo.timeout}`);
	
	// timerArea 상위 -> window 객체
// 	let timerArea = $("#timerArea");
// 	let timeout = timerArea.data("timeout");
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>















