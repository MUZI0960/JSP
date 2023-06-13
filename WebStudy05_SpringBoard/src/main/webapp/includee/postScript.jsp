<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/admin2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${pageContext.request.contextPath }/resources/admin2/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath }/resources/admin2/js/sb-admin-2.min.js"></script>

<script>
	let pushWs = new WebSocket("ws://localhost${cPath}/ws/push");
	pushWs.onmessage = function(event){
		alert(event.data);
	}
</script>