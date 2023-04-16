<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<form action="<c:url value='/file/upload3'/>" method="post" enctype="multipart/form-data">
	<input type="text" name="uploader" />
	<input type="file" name="uploadFile" />
	<input type="submit" value="업로드" />
</form>

<div style="background-color: green;">
	${modelMap }
</div>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>