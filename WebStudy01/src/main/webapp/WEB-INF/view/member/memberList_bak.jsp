<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
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
<% List<MemberVO> list =  (List<MemberVO>) request.getAttribute("memberList"); %>
<table class="table table-bordered">
   <thead>
      <tr>
         <th>회원아이디</th>
         <th>회원명</th>
         <th>생일</th>
         <th>이메일</th>
         <th>휴대폰</th>
         <th>주소1</th>
         <th>기념일</th>
         <th>마일리지</th>
      </tr>
   </thead>
   <tbody>
      <%--
         String ptrn = "<td>%s</td>";
         for(MemberVO vo : list){
            out.println("<tr>");
            
            out.println(String.format(ptrn, vo.getMemId()));
            out.println(String.format(ptrn, vo.getMemName()));
            out.println(String.format(ptrn, vo.getMemBir()));
            out.println(String.format(ptrn, vo.getMemMail()));
            out.println(String.format(ptrn, vo.getMemHp()));
            out.println(String.format(ptrn, vo.getMemAdd1()));
            out.println(String.format(ptrn, vo.getMemMemorial()));
            out.println(String.format(ptrn, vo.getMemMileage()));
            
            out.println("</tr>");
         }
      --%>
      <% for(MemberVO vo : list){ %>
         <tr>
            <td onclick="location.href='<%=request.getContextPath()%>/member/memberView.do?who=<%=vo.getMemId() %>'"><%=vo.getMemId() %></td>
            <td><%=vo.getMemName() %></td>
            <td><%=vo.getMemBir() %></td>
            <td><%=vo.getMemMail() %></td>
            <td><%=vo.getMemHp() %></td>
            <td><%=vo.getMemAdd1() %></td>
            <td><%=vo.getMemMemorial() %></td>
            <td><%=vo.getMemMileage() %></td>
         </tr>
      <%} %>
   </tbody>
</table>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>