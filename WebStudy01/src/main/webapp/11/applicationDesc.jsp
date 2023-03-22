<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.io.File"%>
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
<h4>ServletContext</h4>
<pre>
	서블릿이 운영되는 컨텍스트와 서버(컨테이너)에 대한 정보를 가진 싱글턴 객체
	
	1. context parameter 확보 : <%=application.getInitParameter("imageFolder") %>
	2. context 정보 확보 : <%=application.getContextPath() %>, <%=request.getContextPath() %>
						, <%=request.getServletContext().getContextPath() %>, <%=session.getServletContext().getContextPath() %>
	3. 서버의 정보 확보 : <%=application.getServerInfo() %>
						<%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
	4. 로그 기록 - 일정한 형식으로 남겨야 함
		<%
			application.log(
					String.format("%s - %s, %s", request.getRemoteAddr(), request.getMethod(), request.getRequestURI())
				);
		%>
	5. 현재 컨텍스트의 웹리소스 확보에 활용(***)
		<%
			
			try{
				String imageURL = "/resources/images/cat1.jpg";
				String realPath = application.getRealPath(imageURL);
				File imageFile = new File(realPath);
// 				out.println(imageFile.getCanonicalPath()); /* realPath와 같음. */
			
				String folderURL = "/11";
				String folderPath = application.getRealPath(folderURL);
				File destFile = new File(folderPath, imageFile.getName());

				Files.copy(imageFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		%>
		
</pre>
<img src="<%=request.getContextPath() %>/11/cat1.jpg"/>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>