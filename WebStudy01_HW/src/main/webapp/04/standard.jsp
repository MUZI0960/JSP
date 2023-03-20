<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>표준 JSP 구성 요소</h4>
	<pre>
		JSP ? 서블릿 스펙 기반의 스크립트 형태로 동작하는 템플릿 엔진
		
		jsp 소스의 표준 구성 요소
		1. 정적 텍스트 (Front-end) : 텍스트, HTML, JS, CSS
		2. 동적 요소(Back-end)
			1) 스크립트 요소
				- scriptlet (지역코드화, _JSPService) : <% 
				// java code , 지역변수
					int currentYear = Calendar.getInstance().get(Calendar.YEAR);
				%>
				- expression : <%= "출력값" %>, <%=currentYear %>, <%= txt1 %>
				- directive : <%@ page buffer="16kb"  %> : 현재 JSP 페이지의 환경 설정, 속성으로 설정.
					page(required)
					include (option) : 정적 내포
					taglib (option) : custom tag library 로딩
				- declaration (선언부 - 전역코드화) : 
				<%!
					private static String txt1 = "텍스트1";
					private String txt2 = "텍스트1";
					public void test(){}
				%>
				- comment : <%-- jsp comment --%>
					client side comment : HTML, JS, CSS
						<!-- HTML comment -->
					<script type="text/javascript">
						// JS comment
					</script>
					<style type="text/css">
						/* CSS comment */
					</style>
					server side comment : java comment, jsp comment
						<% // java comment %>
						<%-- jsp comment --%>
			2) EL(expression language)
			3) jsp 액션 태그
			4) JSTL(jsp standard tag library)
			
			
			
		JSP 컨테이너의 역할
			1. 현재 요청을 처리할 JSP 검색 (싱글톤 인스턴스 검색)
			2-1. 검색됐다면, callback 메소드를 호출해 응답 전송
			2-2. 검색에 실패한 경우,
				3. jsp 템플릿 대상으로 서블릿 소스 생성 (work)
				4. 컴파일
				5. 싱글톤 인스턴스 생성
				6. callback 메소드 호출해 응답 전송
		
		Servlet 컨테이너의 역할
			1. 현재 요청을 처리할 서블릿의 인스턴스 검색
			2-1. 검색됐다면, callback 메소드를 호출해 응답 전송
			2-2. 검색에 실패한 경우,
				3. 싱글톤 인스턴스 생성
				4. callback 메소드 호출해 응답 전송
				
	</pre>
	<% 
		request.setAttribute("sampleAttr", "비밀데이터");
	%>
	<script>
<%-- 	let sample = "<%=txt1 %>" --%>
//  	let sample = "${sampleAttr}"; 
	</script>
	
</body>
</html>




