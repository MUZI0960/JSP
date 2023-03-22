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
<script type="text/javascript">
let data = $("#memberSel option:selected").val();
</script>
<form action="<%=request.getContextPath()%>/bts/"  method="get">
	<select id="memberSel">
		<option value="B001" label="RM" selected />
		<option value="B002" label="진" />
		<option value="B003" label="슈가" />
		<option value="B004" label="제이홉" />
		<option value="B005" label="지민" />
		<option value="B006" label="뷔" />
		<option value="B007" label="정국" />
	</select>
</form>
<script type="text/javascript">
let selectMem = $("[name=memberSel]").on("change", function(){
	
let url = $(this).action;
let method = $(this).method;

let data = {};

let data = $("#memberSel option:selected").val();
let data = JSON.stringify(data);

console.log(data);

$.ajax({
	url : url,
	method : method,
	data : data,
	dataType : "json"
}).done(function(resp, textStatus, jqXHR) {

}).fail(function(jqXHR, status, error) {
	console.log(`상태코드 : \${status}, 에러메세지 : \${error}`);
});
	
})

</script>
</body>
</html>