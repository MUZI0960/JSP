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
<form action="<%=request.getContextPath() %>/login/loginProcess" method="post">
	<ul>
		<li>아이디 : <input type="text" name="memId" /></li>
		<li>비밀번호 : <input type="password" name="memPass" /></li>
		<il>
			<input type="submit" value="로그인" />
		</il>
	</ul>
</form>
<script type="text/javascript">
<%
	String message = (String) request.getParameter("message");
%>
	
	let loginForm = $("form").on("submit", function(event){
		event.preventDefault();
		
		let data = JSON.stringify( $(this).serializeObject() );
		$.ajax({
			url : $.CPATH + "/login/loginProcess",
			method : "post",
			data : {"memId" : memId,
					"memPass" :memPass
					}
			dataType : "json"
		}).done(function(resp, textStatus, jqXHR) {
			if(resp.message!=null){
				Swal.fire({
				  icon: 'error',
				  title: 'Oops...',
				  text: resp.message,
				  footer: '<a href="">Why do I have this issue?</a>'
				})
			}
		}).fail(function(jqXHR, status, error) {
			console.log(`상태코드 : \${status}, 에러메세지 : \${error}`);
		});
	
		
		
	})
	
	
	
	
</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>