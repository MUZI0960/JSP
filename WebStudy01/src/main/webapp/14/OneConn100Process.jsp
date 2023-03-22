<%@page import="kr.or.ddit.schema.dao.ColumnSchemaDAOImpl"%>
<%@page import="kr.or.ddit.schema.dao.ColumnSchemaDAO"%>
<%@page import="kr.or.ddit.vo.ColumnSchemaVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.schema.service.SchemaServiceImpl"%>
<%@page import="kr.or.ddit.schema.service.SchemaService"%>
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
<%
      long start = System.currentTimeMillis();
      ColumnSchemaDAO dao = new ColumnSchemaDAOImpl();
      List<ColumnSchemaVO> list= dao.selectColumnSchemaListByTableName("MEMBER");
      for(int i=0;i<100;i++){
         out.println(list);
      }
      long end = System.currentTimeMillis();
      out.println(String.format("소요시간 : %dms", (end-start)));
%>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>