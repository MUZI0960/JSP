<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/js/bootstrap-5.2.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/sweetalert2/sweetalert2.all.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/js/sweetalert2/sweetalert2.min.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.serializeObject.min.js"></script>
<script>
	$.CPATH = "<%=request.getContextPath()%>";
	$(document).ajaxError(function(event, jqXHR, settings, error){
		console.log(`비동기 요청[\${settings.url}, \${settings.type}] 실패, 상태코드 : \${jqXHR.status}, 에러메세지 : \${error}`);
	});
</script>