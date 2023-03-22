<%@page import="kr.or.ddit.calculator.Operator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.3.min.js"></script>
</head>
<body>
<form id="calForm" action="<%=request.getContextPath() %>/calculator/case2">
	<input type="number" name="leftOp">
	<select name="operator">
		<%
			String optPtrn = "<option value='%s'>%c</option>";
			for(Operator single : Operator.values()){
				out.println(
					String.format(optPtrn, single.name(), single.getSign())		
				);
			}
		
		%>
	</select>
	<input type="number" name="rightOp" />
	<input type="submit" value="=" />
</form>

<div id="resultArea"></div>

<script type="text/javascript">
let resultArea = $('#resultArea');
$("#calForm").on("submit", function(event) {
	event.preventDefault();

	let url = this.action;
	let method = this.method;
	let data = $(this).serialize(); // url encoded string
	console.log(data)
	
	$.ajax({
		url :url,
		method : method,
		data : data,
		dataType : "html"
	}).done(function(resp, textStatus, jqXHR) {
		resultArea.html(resp);
	}).fail(function(jqXHR, status, error) {
		console.log(`상태코드 : \${status}, 에러메세지 : \${error}`);
	});
	
	
	return false;
	
	
	
	
})

</script>

</body>
</html>