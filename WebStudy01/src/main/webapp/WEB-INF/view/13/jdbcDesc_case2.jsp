<%@page import="kr.or.ddit.props.vo.PropertyVO"%>
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

<table>
	<thead>
		<tr>
			<th>PROPERTY_NAME</th>
			<th>PROPERTY_VALUE</th>
			<th>DESCRIPTION</th>
		</tr>
	</thead>
	<tbody>
		<%
			List<PropertyVO> list = (List)request.getAttribute("list"); 	
		
			StringBuffer sb = new StringBuffer();
			String ptrn = "<td>%s</td>";
			for(int i = 0; i<list.size(); i++){
				sb.append("<tr>");
				PropertyVO vo = list.get(i);
				String name = vo.getPropertyName();
				String value = vo.getPropertyValue();
				String dc = vo.getDescription();
				
				sb.append(String.format(ptrn,name));
				sb.append(String.format(ptrn,value));
				sb.append(String.format(ptrn,dc));
			}
			
		%>
	</tbody>
	
</table>

<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>