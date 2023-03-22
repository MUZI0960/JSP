<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Objects"%>
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
<h4>EL(Expression Language, 표현 언어)</h4>
<pre>
	: Scope를 통해 공유되는 속성값을 조회할 목적으로 사용되는 *스크립트 형태의 언어. 
	<%=Objects.toString(request.getAttribute("attributeName"), "") %>	${attributeName }
	
	<%
		pageContext.setAttribute("attr", "페이지속성");
		pageContext.setAttribute("attrNum", "3");
		pageContext.setAttribute("attrNum2-2", "3");
		request.setAttribute("attr", "요청속성");
		session.setAttribute("a ttr", "세션 속성");
	%>
	1. 속성데이터에 대한 접근 방법 : ${attributeName }
		${attr }, ${requestScope.attr }, ${requestScope['attr'] }, ${sessionScope['a ttr'] }
	2. EL 연산자
		1) 산술연산자 : + - * / %
			${3+2 }, ${"3"+2 }, ${"3"+"2" }, \${"a"+3 }
			${3/2 } => 연산이 수행될 때 타입 결정됨
			${attrNum+2 }
			${attrNum2+2 } attrNum2=> null 되어서 0 => 2만 출력됨
			${pageScope['attrNum2-2'] }
			
		2) 논리연산자 : && and, || or, ! not (키워드를 더 많이 사용)
			${true and true }, ${attrBool and true }
			
		3) 비교연산자 : &gt; gt, %lt; lt, >= ge, <= le, == eq, != ne
			${3 lt 4 }, ${3 ge 4 }, ${3 eq 4 }, ${3 ne 4 }
			${pageScope.attr eq requestScope.attr }
			
		4) 단항연산자 : empty <% pageContext.setAttribute("sample", " "); %> length 체크 0보다 크면 true
			${empty sesseionScope.attr }, ${empty sample }
			<%
			pageContext.setAttribute("array", new String[]{}); 
			pageContext.setAttribute("list", new ArrayList<>()); 
			%>
			${not empty array }, ${not empty list } length, size 알아서 사용
			
		5) 삼항연산자 : 조건식? 참표현 : 거짓표현
			${empty array ? "빈배열" : "채워진배열" }, ${not empty list ? "채워진리스트" : "빈리스트" }
		
	3. 속성 객체의 구성멤버 접근 방법
	<%
		MemberVO member = MemberVO.builder()
									.memName("이름")
									.build();
		pageContext.setAttribute("aMem", member);
	%>
	${aMem.memName }, ${aMem.memTest }, ${aMem['memTest'] }
	4. 속성 컬렉션 요소에 대한 접근 방법
	<%
		String[] array = new String[]{"value1", "value2"};
		List<String> list = Arrays.asList(array);
		Set<String> set = new HashSet(list);
		Map<String, Object> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key-3", "value3");
		pageContext.setAttribute("attrAray", array);
		pageContext.setAttribute("attrList", list);
		pageContext.setAttribute("attrSet", set);
		pageContext.setAttribute("attrMap", map);
	%>
	${attrAray[1] }
	${attrList[1] }, \${attrList.get(4) } -> 오류시 500에러 발생으로 사용 X
	${attrSet }
	${attrMap['key2'] }, ${attrMap.key2 }, ${attrMap['key-3'] }
	
	5. EL 기본객체 : 
		1) 영역 객체 : pageScope, requestScope, sessionScope, applicationScope
		2) 요청 파라미터 객체 : param(Map<String,String>), paramValues(Map<String,String[]>)
		<%=request.getParameter("name1") %>, ${param.name1 }, ${param['name1'] }
		<%=request.getParameterValues("name1") %>, ${paramValues.name1[0] } ${paramValues['name1'][0] }
		3) 요청 헤더 객체 : header(Map<String,String>), headerValues(Map<String,String[]>)
		<%=request.getHeader("user-agent") %>, ${header['user-agent'] }
		4) 요청 쿠키 객체 : cookie(Map<Stiring,Cookie>)
		<%
			Cookie[] cookies = request.getCookies();
			for(Cookie tmp : cookies){
				if("JSESSIONID".equals(tmp.getName())){
					out.println(tmp.getValue());
				}
			}
		%>
		${cookie.JSESSIONID.value }, ${cookie['JSESSIONID']['value'] } 
		5) 컨텍스트 파라미터 객체 : initParam(Map<String,String>)
		${initParam.imageFolder }
		6) pageContext
		${pageContext.request.contextPath }
</pre>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>






