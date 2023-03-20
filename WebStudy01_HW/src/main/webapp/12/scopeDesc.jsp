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
<h4>Scope (영역)</h4>
<pre>
	명확한 생명주기를 갖는 4개의 기본 객체가 가진 데이터 공유 영역(저장공간 : Map)
	--> attribute(name:String/value:Object) : 해당 영역을 통해 공유되는 데이터
	pageScope (PageContext) - 커스텀 태그 사용 시 주로 사용 : 동일 JSP 페이지 내에서만 공유되는 영역 (데이터 공유 목적으로 사용 불가능)
	requestScope (HttpServletRequest) : request 의 생명주기가 동일한 공유 영역(dispatch 이동의 경유, 공유 가능)
	sessionScope (HttpSession) : session 의 생명주기와 동일한 공유 영역 (개인/클라이언트의 고유 저장 공간으로 활용)
	applicationScope (ServletContext) : application 의 생명주기와 동일.
	
	setAttribute(name, value), getAttribute(name), removeAttribute(name)
	
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr", "요청 속성");
		session.setAttribute("sessionAttr", "세션 속성");
		application.setAttribute("applicationAttr", "어플리케이션 속성");
		
		String path = "/12/attributeView.jsp";
// 		pageContext.include(path);
// 		response.sendRedirect(request.getContextPath() + path);
	%>
		
</pre>
<a href="<%=request.getContextPath()+path%>">attributeView.jsp로 이동</a>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>


