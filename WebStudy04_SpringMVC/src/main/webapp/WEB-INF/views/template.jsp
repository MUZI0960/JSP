<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<tiles:insertAttribute name="preScript"/>
<c:if test="${not empty message }">
	<script type="text/javascript">
		window.addEventListener("DOMContentLoaded", function(){
			Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: '${message}'
				})
		});
	</script>
</c:if>
</head>
<body>
<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="postScript"/>
</body>
</html>