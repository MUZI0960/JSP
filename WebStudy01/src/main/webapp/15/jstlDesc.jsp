<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<h4>JSTL(Jsp Standard Tag Library)</h4>
<pre>
	: 커스텀 태그의 모음 라이브러리
<%-- 	<prefix:tagname attributess... /> --%>
	1. Core 태그 종류
		1) EL 변수(속성) 지원 : set, remove
<%-- 			<c:set var="sample" value="샘플" scope="session" /> --%>
			${sample }
			<c:remove var="sample" scope="session"/>
		2) 조건문 : if, choose~when~otherwise
			<c:if test="${not empty sample }">
				${sample }==> 있다
			</c:if>
			<c:if test="${empty sample }">
				없다.
			</c:if>
			
			<c:choose>
				<c:when test="${not empty sample }">
					${sample }==> 있다
				</c:when>
					<c:otherwise>
						없다.
					</c:otherwise>
			</c:choose>
		3) 반복문 : forEach, forTokens
			for(선언절 ; 조건절 ; 증감절)
			for(블럭변수 선언 : 컬렉션)
			step = "1" 생략 가능
			<c:forEach var="i" begin="1" end="10" step="2" varStatus="lts">
				${i * 100 } ${not lts.last ? "," : ""}	, INDEX : ${lts.index } , ${lts.count }번째 반복			
			</c:forEach>
			
			<c:set var="attrList" value="<%=Arrays.asList(1,2,3,4) %>"></c:set>
			<c:forEach items="${attrList }" var="num">
				${num }
			</c:forEach>
			
			토큰 : 최소한으로 쪼개진 단위
			inti=3;
			아버지가방에들어가신다 ... 
			아버지가 방에 들어가신다 (토큰:3)
			<c:forTokens items="아버지가 방에 들어가신다" delims=" " var="token">
				${token }
			</c:forTokens> 
			
			<c:forTokens items="1,2,3,4" delims="," var="str">
				${str * 1000 }
			</c:forTokens>
		4) URL 재처리(rewrite) : url
			<c:url value="/member/memberView.do" var="viewURL">
				<c:param name="who" value="a001" />
				<c:param name="name" value="asdf" />
			</c:url>
			${viewURL }
		5) 기타 : redirect, out, import
<%-- 		<c:redirect url="/" /> --%>
			<%-- 스크랩핑 --%>
			<c:import url="https://www.naver.com" var="naver"/>
			<%-- html source escape 해서 출력 --%>
<%-- 			<c:out value="${naver }" escapeXml="false" /> --%>
			

</pre>


<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>







