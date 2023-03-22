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
<button id="loadBtn">Data Load!</button>
<table>
	<thead>
		<tr>
			<th>프로퍼티명</th>
			<th>프로퍼티값</th>
		</tr>
	</thead>
	<tbody id="listBody">
	
	</tbody>
</table>

<form id="insertForm" method="post" action="<%=request.getContextPath() %>/props">
	<input type="text" name="propertyName" placeholder="property name" />
	<input type="text" name="propertyValue" placeholder="property value" />
	<input type="submit" value="저장" />
</form>
<!-- 플러그인 -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/packages/commons/custom.js"></script>
<script type="text/javascript">

// DOM (Document Object Model)
let success = function(resp, textStatus, jqXHR){
	if(jqXHR.responseJSON){
		if(resp.error){
			alert(resp.message);			
		}else{
			let trTags = [];
			list = resp.list
			list.forEach(prop => {
			let tr = $("<tr>").append(
							$("<td>").html(prop.propertyName)
							, $("<td>").html(prop.propertyValue)
							, $("<td>").html(
								$("<button>")
								.addClass("deleteBtn")
								.text("삭제")		
							)
						).data("source", prop);
			trTags.push(tr);
			});
			listBody.html(trTags);
			insertForm[0].reset();
		}
}
}
let insertForm = $("#insertForm").on("submit", function(event){
	event.preventDefault();
	let url = this.action;
	let method = this.method;
	let data = JSON.stringify( $(this).getObject() );
	
	$.ajax({
		url : url,
		method : method,
		contentType: "applicaion/json;charset=UTF-8",
		data : data,
		dataType : "json"
	}).done(success)
	.fail(function(jqXHR, status, error) {
		console.log(`상태코드 : \${status}, 에러메세지 : \${error}`);
	});
	return false;
})

// 이벤트 버블링..?
let listBody = $('#listBody').on("click", ".deleteBtn", function(){
// 	console.log("클릭");	
	let prop = $(this).parents("tr:first").data("source");
	console.log(prop);	
	$.ajax({
		url : "<%=request.getContextPath() %>/props",
		method : "delete",
		contentType: "applicaion/json;charset=UTF-8",
		data : JSON.stringify(prop),
		dataType : "json"
	}).done(success).fail(function(jqXHR, status, error) {
		console.log(`상태코드 : \${status}, 에러메세지 : \${error}`);
	});
	
});

$('#loadBtn').on('click', function(){
	alert("Test")
	$.ajax({
		url : "<%=request.getContextPath() %>/props",
		method : "get",
		dataType : "json"
	}).done(success)
	.fail(function(jqXHR, status, error) {
		console.log(`상태코드 : \${status}, 에러메세지 : \${error}`);
	});
}).trigger("click");


</script>
</body>
</html>